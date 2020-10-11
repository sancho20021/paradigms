(defn variable [name] (fn [vars] (vars name)))
(def constant constantly)
(defn createOperation [operation] (fn [& args]
                                      (fn [params] (apply operation (mapv (fn [arg] (arg params)) args)))))
(defn _div
      ([a] (/ (double a)))
      ([a & rst] (reduce (fn [a b] (/ (double a) (double b))) a rst)))
(def _exp #(Math/exp %))
(defn _sumexp [& args] (apply + (mapv _exp args)))

(def add (createOperation +))
(def subtract (createOperation -))
(def multiply (createOperation *))
(def divide (createOperation _div))
(def negate subtract)
(def sumexp (createOperation _sumexp))
(def softmax (createOperation (fn [& args] (_div (_exp (first args)) (apply _sumexp args)))))

(def TOKEN_TO_FUNCS
  {'+       add,
   '-       subtract,
   '*       multiply,
   '/       divide,
   'negate  negate
   'sumexp  sumexp
   'softmax softmax})

(defn commonParseFunction [opsMap cnst vr]
      (fn [expression] (letfn [(parse [token]
                                      (cond
                                        (list? token) (apply (opsMap (first token)) (mapv parse (rest token)))
                                        (number? token) (cnst token)
                                        :else (vr (str token))))]
                              (parse (read-string expression)))))
(def parseFunction (commonParseFunction TOKEN_TO_FUNCS constant variable))

(defn proto-get [obj key]
      (cond (contains? obj key) (obj key)
            (contains? obj :proto) (proto-get (obj :proto) key)))

(defn field [key]
      #(proto-get % key))

(defn proto-call [obj key & args]
      (apply (proto-get obj key) obj args))

(defn method [key]
      (fn [obj & args] (apply proto-call obj key args)))

(defn constructor [cons proto]
      (fn [& args] (apply cons {:proto proto} args)))

(declare ONE ZERO)
(def evaluate (method :evaluate))
(def toString (method :toString))
(def diff (method :diff))

(defn getExprProto [evaluate toString diff]
      {:evaluate evaluate
       :toString toString
       :diff     diff
       })

(def Variable (constructor
                (fn [this name] (assoc this :name name))
                (let [_name (field :name)]
                     (getExprProto
                       (fn [this vars] (vars (_name this)))
                       #(_name %)
                       (fn [this name] (if (= (_name this) name) ONE ZERO))))))

(def Constant (constructor
                (fn [this value] (assoc this :value value))
                (let [_value (field :value)]
                     (getExprProto
                       (fn [this vars] (_value this))
                       #(format "%.1f" (double (_value %)))
                       (fn [this name] ZERO)))))
(def ZERO (Constant 0))
(def ONE (Constant 1))

(def _dfunction (method :dfunction))
(def OperProto
  (let [_args (field :args)
        _function (field :function)
        _symbol (field :symbol)]
       (getExprProto
         (fn [this vars] (apply (_function this) (mapv #(evaluate % vars) (_args this))))
         #(str "(" (_symbol %) " " (clojure.string/join " " (mapv toString (_args %))) ")")
         (fn [this name] (_dfunction this (_args this) (mapv #(diff % name) (_args this)))))))
(defn Operation [function symbol dfunction]
      (constructor
        (fn [this & args] (assoc this :args args))
        {:proto     OperProto
         :function  function
         :symbol    symbol
         :dfunction (fn [this args dargs] (dfunction args dargs))}))

(defn simpleDif [Op] (fn [args dargs] (apply Op dargs)))

(declare Negate)
(def Negate (Operation - 'negate (simpleDif (fn [& args] (apply Negate args)))))

(declare Add)
(def Add (Operation + '+ (simpleDif (fn [& args] (apply Add args)))))

(declare Subtract)
(def Subtract (Operation - '- (simpleDif (fn [& args] (apply Subtract args)))))

(declare Multiply)
(def Multiply
  (Operation
    * '* (fn [args dargs] (second (reduce
                                    (fn [[a a'] [b b']] [(Multiply a b)
                                                         (Add (Multiply a' b) (Multiply a b'))])
                                    (mapv vector args dargs))))))

(declare Divide)
(defn div_dfunction [[a & rst] [da & drst]]
      (let [denominator (apply Multiply rst)]
           (if (empty? rst)
             (Negate (Divide da (Multiply a a)))
             (Divide (Subtract
                       (apply Multiply da rst)
                       (Multiply a (_dfunction denominator rst drst)))
                     (Multiply denominator denominator)))))
(def Divide (Operation _div '/ div_dfunction))

(declare Sumexp)
(defn sumexp_dfunction [args dargs]
      (apply Add (mapv (fn [x y] (Multiply (Sumexp x) y)) args dargs)))
(def Sumexp (Operation _sumexp 'sumexp sumexp_dfunction))

(def Softmax (Operation
               (fn [& args] (_div (_exp (first args)) (apply _sumexp args)))
               'softmax
               (fn [args d_args]
                   (div_dfunction [(Sumexp (first args)) (apply Sumexp args)]
                                  [(sumexp_dfunction [(first args)] [(first d_args)])
                                   (sumexp_dfunction args d_args)]))))
(def TOKEN_TO_OBJS {'+       Add
                    '-       Subtract
                    '*       Multiply
                    '/       Divide
                    'negate  Negate
                    'sumexp  Sumexp
                    'softmax Softmax})
(def parseObject (commonParseFunction TOKEN_TO_OBJS Constant Variable))