# Тесты к курсу «Парадигмы программирования»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/paradigms/homeworks.html)


## Домашнее задание 9. Линейная алгебра на Clojure

Модификации
 * *Базовая*
    * Код должен находиться в файле `linear.clj`.
    * Исходный код тестов
        * [Простой вариант](clojure/cljtest/linear/LinearBinaryTest.java)
        * [Сложный вариант](clojure/cljtest/linear/LinearNaryTest.java)


## Исходный код к лекциям по Clojure

Запуск Clojure
 * Консоль: [Windows](clojure/RunClojure.cmd), [*nix](clojure/RunClojure.sh)
    * Интерактивный: `RunClojure`
    * С выражением: `RunClojure --eval "<выражение>"`
    * Скрипт: `RunClojure <файл скрипта>`
    * Справка: `RunClojure --help`
 * IDE
    * IntelliJ Idea: [плагин Cursive](https://cursive-ide.com/userguide/)
    * Eclipse: [плагин Counterclockwise](https://doc.ccw-ide.org/documentation.html)

[Скрипт со всеми примерами](clojure/examples.clj)

Лекция 1. Функции
 * [Введение](clojure/examples/1_1_intro.clj)
 * [Функции](clojure/examples/1_2_functions.clj)
 * [Списки](clojure/examples/1_3_lists.clj)
 * [Вектора](clojure/examples/1_4_vectors.clj)
 * [Функции высшего порядка](clojure/examples/1_5_functions-2.clj)


## Домашнее задание 8. Обработка ошибок на JavaScript

Модификации
 * *Базовая*
    * Код должен находиться в файле `objectExpression.js`.
    * [Исходный код тестов](javascript/jstest/prefix/PrefixParserTest.java)
        * Запускать c аргументом `easy` или `hard`
 * *PrefixAtanExp*. Дополнительно реализовать поддержку:
    * унарных операций:
        * `ArcTan` (`atan`) – арктангенс, `(atan 2)` примерно равно 1.1;
        * `Exp` (`Exp`) – экспонента, `(exp 3)` примерно равно 20;
    * [Исходный код тестов](javascript/jstest/prefix/PrefixAtanExpTest.java)
 * *PostfixSumAvg*. Дополнительно реализовать поддержку:
    * выражений в постфиксной записи: `(2 3 +)` равно 5
    * унарных операций:
        * `Sum` (`sum`) – сумма, `(1 2 3 sum)` равно 6;
        * `Avg` (`avg`) – арифметическое среднее, `(1 2 3 avg)` равно 2;
    * [Исходный код тестов](javascript/jstest/prefix/PostfixSumAvgTest.java)
 * *PostfixSumexpSoftmax*. Дополнительно реализовать поддержку:
    * выражений в постфиксной записи: `(2 3 +)` равно 5
    * унарных операций:
        * `Sumexp` (`sumexp`) – сумма экспонент, `(8 8 9 sumexp)` примерно равно 14065;
        * `Softmax` (`softmax`) – [softmax](https://ru.wikipedia.org/wiki/Softmax) первого аргумента, `(1 2 3 softmax)` примерно 0.09;
    * [Исходный код тестов](javascript/jstest/prefix/PostfixSumexpSoftmaxTest.java)
 * *PrefixSinhCosh*. Дополнительно реализовать поддержку:
    * унарных операций:
        * `Sinh` (`sinh`) – гиперболический синус, `(sinh 3)` немного больше 10;
        * `Cosh` (`cosh`) – гиперболический косинус, `(cosh 3)` немного меньше 10;
    * [Исходный код тестов](javascript/jstest/prefix/PrefixSinhCoshTest.java)

## Домашнее задание 7. Объектные выражения на JavaScript

Модификации
 * *Базовая*
    * Код должен находиться в файле `objectExpression.js`.
    * [Исходный код тестов](javascript/jstest/object/ObjectExpressionTest.java)
        * Запускать c аргументом `easy`, `hard` или `bonus`.
 * *MinMax*. Дополнительно реализовать поддержку:
    * функций:
        * `Min3` (`min3`) – минимум из трех аргументов, `1 2 3 min` равно 1;
        * `Max5` (`max5`) – максимум из пяти аргументов, `1 2 3 4 5 max` равно 5;
    * [Исходный код тестов](javascript/jstest/object/ObjectMinMaxTest.java)
 * *PowLog*. Дополнительно реализовать поддержку:
    * бинарных операций:
        * `Power` (`pow`) – возведение в степень, `2 3 pow` равно 8;
        * `Log` (`log`) – логарифм абсолютного значения аргумента
            по абсолютному значению основания `-2 -8 log` равно 3;
    * [Исходный код тестов](javascript/jstest/object/ObjectPowLogTest.java)
 * *SinhCosh*. Дополнительно реализовать поддержку:
    * унарных функций:
        * `Sinh` (`sinh`) – гиперболический синус, `3 sinh` немного больше 10;
        * `Cosh` (`cosh`) – гиперболический косинус, `3 cosh` немного меньше 10;
    * [Исходный код тестов](javascript/jstest/object/ObjectSinhCoshTest.java)
 * *Gauss*. Дополнительно реализовать поддержку:
    * функций:
        * `Gauss` (`gauss`) – [функция Гаусса](https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D1%83%D1%81%D1%81%D0%BE%D0%B2%D0%B0_%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D1%8F);
          от четырех аргументов: `a`, `b`, `c`, `x`.
    * [Исходный код тестов](javascript/jstest/object/ObjectGaussTest.java)


## Домашнее задание 6. Функциональные выражения на JavaScript

Модификации
 * *Базовая*
    * Код должен находиться в файле `functionalExpression.js`.
    * [Исходный код тестов](javascript/jstest/functional/FunctionalExpressionTest.java)
        * Запускать c аргументом `hard` или `easy`;
 * *Mini*
    * Не поддерживаются бинарные операции
    * Код находится в файле [functionalMiniExpression.js](javascript/functionalMiniExpression.js).
    * [Исходный код тестов](javascript/jstest/functional/FunctionalMiniTest.java)
        * Запускать c аргументом `hard` или `easy`;
 * *PieSinCos*. Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `pi` – π;
        * `e` – основание натурального логарифма;
    * операций:
        * `sin` – синус, `pi sin` равно 0;
        * `cos` – косинус, `pi cos` равно -1.
    * [Исходный код тестов](javascript/jstest/functional/FunctionalPieSinCosTest.java)
        * Запускать c аргументом `hard` или `easy`
 * *Cube*. Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * унарных функций:
        * `cube` – возведение в куб, `2 cube` равно 8;
        * `cuberoot` – кубический корень, `-8 cuberoot` равно -2;
    * [Исходный код тестов](javascript/jstest/functional/FunctionalCubeTest.java)
 * *PieAvgMed*. Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `pi` – π;
        * `e` – основание натурального логарифма;
    * операций:
        * `avg5` – арифметическое среднее пяти аргументов, `1 2 3 4 5 avg5` равно 7.5;
        * `med3` – медиана трех аргументов, `1 2 -10 med3` равно 1.
    * [Исходный код тестов](javascript/jstest/functional/FunctionalPieAvgMedTest.java)
        * Запускать c аргументом `hard` или `easy`
 * *OneIffAbs*. Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `one` – 1;
        * `two` – 2;
    * операций:
        * `abs` – абсолютное значение, `-2 abs` равно 2;
        * `iff` – условный выбор:
            если первый аргумент неотрицательный,
            вернуть второй аргумент,
            иначе вернуть первый третий аргумент.
            * `one two 3 iff` равно 2
            * `-1 -2 -3 iff` равно -3
            * `0 one two iff` равно 1;
    * [Исходный код тестов](javascript/jstest/functional/FunctionalOneIffAbsTest.java)
        * Запускать c аргументом `hard` или `easy`

Запуск тестов
 * Для запуска тестов используется [GraalJS](https://github.com/graalvm/graaljs)
   (часть проекта [GraalVM](https://www.graalvm.org/), вам не требуется их скачивать отдельно)
 * Для запуска тестов можно использовать скрипты [TestJS.cmd](javascript/TestJS.cmd) и [TestJS.sh](javascript/TestJS.sh)
    * Репозиторий должен быть скачан целиком.
    * Скрипты должны находиться в каталоге `javascript` (их нельзя перемещать, но можно вызывать из других каталогов).
 * Для самостоятельно запуска из консоли необходимо использовать командную строку вида:
    `java -ea --module-path=<js>/graal --class-path <js> jstest.functional.FunctionalExpressionTest {hard|easy}`, где
    * `-ea` – включение проверок времени исполнения;
    * `--module-path=<js>/graal` путь к модулям Graal (здесь и далее `<js>` путь к каталогу `javascript` этого репозитория);
    * `--class-path <js>` путь к откомпилированным тестам;
    * {`hard`|`easy`} указание тестируемой модификации.
 * При запуске из IDE, обычно не требуется указывать `--class-path`, так как он формируется автоматически.
   Остальные опции все равно необходимо указать.
 * Troubleshooting
    * `Error occurred during initialization of boot layer java.lang.module.FindException: Module org.graalvm.truffle not found, required by jdk.internal.vm.compiler` – неверно указан `--module-path`;
    * `Graal.js not found` – неверно указаны `--module-path`
    * `Error: Could not find or load main class jstest.functional.FunctionalExpressionTest` – неверно указан `--class-path`;
    * `Error: Could not find or load main class <other class>` – неверно указано полное имя класса теста;
    * `Exception in thread "main" java.lang.AssertionError: You should enable assertions by running 'java -ea jstest.functional.FunctionalExpressionTest'` – не указана опция `-ea`;
    * `First argument should be one of: "easy", "hard", found: XXX` – неверно указана сложность;
    * `Exception in thread "main" jstest.EngineException: Script 'functionalExpression.js' not found` – в текущем каталоге отсутствует решение (`functionalExpression.js`)


## Исходный код к лекциям по JavaScript

[Скрипт с примерами](javascript/examples.js)

Запуск примеров
 * [В браузере](javascript/RunJS.html)
 * Из консоли
    * [на Java](javascript/RunJS.java): [RunJS.cmd](javascript/RunJS.cmd), [RunJS.sh](javascript/RunJS.sh)
    * [на node.js](javascript/RunJS.node.js): `node RunJS.node.js`

Лекция 1. Типы и функции
 * [Типы](javascript/examples/1_1_types.js)
 * [Функции](javascript/examples/1_2_functions.js)
 * [Функции высшего порядка](javascript/examples/1_3_functions-hi.js).
   Обратите внимание на реализацию функции `mCurry`.

Лекция 2. Объекты и методы
 * [Объекты](javascript/examples/2_1_objects.js)
 * [Замыкания](javascript/examples/2_2_closures.js)
 * [Модули](javascript/examples/2_3_modules.js)
 * [Пример: стеки](javascript/examples/2_4_stacks.js)

Лекция 3. Другие возможности
 * [Обработка ошибок](javascript/examples/3_1_errors.js)
 * [Чего нет в JS](javascript/examples/3_2_no.js)
 * [Стандартная библиотека](javascript/examples/3_3_builtins.js)
 * [Работа со свойствами](javascript/examples/3_4_properties.js)
 * [JS 6+](javascript/examples/3_5_js6.js)


## Домашнее задание 5. Вычисление в различных типах

Модификации
 * *Базовая*
    * Класс `GenericTabulator` должен реализовывать интерфейс
      [Tabulator](java/expression/generic/Tabulator.java) и
      сроить трехмерную таблицу значений заданного выражения.
        * `mode` – режим вычислений:
           * `i` – вычисления в `int` с проверкой на переполнение;
           * `d` – вычисления в `double` без проверки на переполнение;
           * `bi` – вычисления в `BigInteger`.
        * `expression` – выражение, для которого надо построить таблицу;
        * `x1`, `x2` – минимальное и максимальное значения переменной `x` (включительно)
        * `y1`, `y2`, `z1`, `z2` – аналогично для `y` и `z`.
        * Результат: элемент `result[i][j][k]` должен содержать
          значение выражения для `x = x1 + i`, `y = y1 + j`, `z = z1 + k`.
          Если значение не определено (например, по причине переполнения),
          то соответствующий элемент должен быть равен `null`.
    * [Исходный код тестов](java/expression/generic/GenericTest.java)
 * *Сmm*
    * Дополнительно реализовать унарные операции:
        * `count` – число установленных битов, `count 5` равно 2.
    * Дополнительно реализовать бинарную операцию (минимальный приоритет):
        * `min` – минимум, `2 min 3` равно 2;
        * `max` – максимум, `2 max 3` равно 3.
    * [Исходный код тестов](java/expression/generic/GenericCmmTest.java)
 * *Ls*
    * Дополнительно реализовать поддержку режимов:
        * `l` – вычисления в `long` без проверки на переполнение;
        * `s` – вычисления в `short` без проверки на переполнение.
    * [Исходный код тестов](java/expression/generic/GenericLsTest.java)
 * *CmmUls*
    * Реализовать операции из модификации *Cmm*.
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `l` – вычисления в `long` без проверки на переполнение;
        * `s` – вычисления в `s` без проверки на переполнение.
    * [Исходный код тестов](java/expression/generic/GenericCmmUlsTest.java)
 * *CmmUfb*
    * Реализовать операции из модификации *Cmm*.
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `f` – вычисления в `float` без проверки на переполнение;
        * `b` – вычисления в `byte` без проверки на переполнение.
    * [Исходный код тестов](java/expression/generic/GenericCmmUfbTest.java)


## Домашнее задание 4. Очередь на связном списке

Модификации
 * *Базовая*
    * [Исходный код тестов](java/queue/QueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueTest.jar)
 * *ToArray*
    * Добавить в интерфейс очереди и реализовать метод
      `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту
    * Исходная очередь должна оставаться неизменной
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/QueueToArrayTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueToArrayTest.jar)
 * *Functions*
    * Добавить в интерфейс очереди и реализовать методы
        * `filter(predicate)` – создать очередь, содержащую элементы, удовлетворяющие
            [предикату](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html)
        * `map(function)` – создать очередь, содержащую результаты применения
            [функции](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Function.html)
    * Исходная очередь должна остаться неизменной
    * Тип возвращаемой очереди должен соответствовать типу исходной очереди
    * Взаимный порядок элементов должен сохраняться
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/QueueFunctionsTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueFunctionsTest.jar)
 * *IfWhile*
    * Добавить в интерфейс очереди и реализовать методы
        * `removeIf(predicate)` – удалить элементы, удовлетворяющие
            [предикату](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html)
        * `retainIf(predicate)` – удалить элементы, не удовлетворяющие
            [предикату](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html)
        * `takeWhile(predicate)` – сохранить подряд идущие элементы, удовлетворяющие
            [предикату](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html)
        * `dropWhile(predicate)` – удалить подряд идущие элементы, не удовлетворяющие
            [предикату](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html)
    * Взаимный порядок элементов должен сохраняться
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/QueueIfWhileTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueIfWhileTest.jar)


## Домашнее задание 3. Очередь на массиве

Модификации
 * *Базовая*
    * Классы должны находиться в пакете `queue`
    * [Исходный код тестов](java/queue/ArrayQueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueTest.jar)
 * *ToStr* (простая)
    * Реализовать метод `toStr`, возвращающий строковое представление
      очереди в виде '`[`' _голова_ '`, `' ... '`, `' _хвост_ '`]`'
    * [Исходный код тестов](java/queue/ArrayQueueToStrTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueToStrTest.jar)
 * *ToArray* (простая)
    * Реализовать метод `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту.
    * Исходная очередь должна остаться неизменной
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/ArrayQueueToArrayTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueToArrayTest.jar)
 * *Deque* (сложная)
    * Реализовать методы
        * `push` – добавить элемент в начало очереди
        * `peek` – вернуть последний элемент в очереди
        * `remove` – вернуть и удалить последний элемент из очереди
    * [Исходный код тестов](java/queue/ArrayQueueDequeTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueDequeTest.jar)
 * *IndexedDeque*
    * Реализовать модификацию *Deque*
    * Реализовать методы
        * `get` – получить элемент по индексу, отсчитываемому с головы
        * `set` – заменить элемент по индексу, отсчитываемому с головы
    * [Исходный код тестов](java/queue/ArrayQueueIndexedDequeTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueIndexedDequeTest.jar)


## Домашнее задание 2. Бинарный поиск

Модификации
 * *Базовая*
    * Класс `BinarySearch` должен находиться в пакете `search`
    * [Исходный код тестов](java/search/BinarySearchTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchTest.jar)
 * *Missing*
    * Если в массиве `a` отсутствует элемент, равный `x`, то требуется
      вывести индекс вставки в формате, определенном в
      [`Arrays.binarySearch`](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#binarySearch-int:A-int-).
    * Класс должен иметь имя `BinarySearchMissing`
    * [Исходный код тестов](java/search/BinarySearchMissingTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchMissingTest.jar)
 * *Span*
    * Требуется вывести два числа: начало и длину диапазона элементов,
      равных `x`. Если таких элементов нет, то следует вывести
      пустой диапазон, у которого левая граница совпадает с местом
      вставки элемента `x`.
    * Не допускается использование типов `long` и `BigInteger`.
    * Класс должен иметь имя `BinarySearchSpan`
    * [Исходный код тестов](java/search/BinarySearchSpanTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchSpanTest.jar)
 * *Shift*
    * На вход подается отсортированный массив, циклически сдвинутый на `k`
      элементов. Требуется найти `k`. Все числа в массиве различны.
    * Класс должен иметь имя `BinarySearchShift`
    * [Исходный код тестов](java/search/BinarySearchShiftTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchShiftTest.jar)

## Домашнее задание 1. Обработка ошибок

Модификации
 * *Базовая*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [Parser](java/expression/exceptions/Parser.java)
    * Классы `CheckedAdd`, `CheckedSubtract`, `CheckedMultiply`,
        `CheckedDivide` и `CheckedNegate` должны реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * Нельзя использовать типы `long` и `double`
    * Нельзя использовать методы классов `Math` и `StrictMath`
    * [Исходный код тестов](java/expression/exceptions/ExceptionsTest.java)
 * *PowLog2*
    * Дополнительно реализуйте унарные операции:
        * `log2` – логарифм по уснованию 2, `log2 10` равно 3;
        * `pow2` – два в степени, `pow2 4` равно 16.
    * [Исходный код тестов](java/expression/exceptions/ExceptionsPowLog2Test.java)
 * *PowLog*
    * Дополнительно реализуйте бинарные операции (максимальный приоритет):
        * `**` – возведение в степень, `2 ** 3` равно 8;
        * `//` – логарифм, `10 // 2` равно 3.
    * [Исходный код тестов](java/expression/exceptions/ExceptionsPowLogTest.java)
