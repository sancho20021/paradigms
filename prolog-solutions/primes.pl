setOfComps(1).
divides(Divisor, N):- N mod Divisor =:= 0.

nextPrime(X, Next):- setOfComps(X), Z is X + 1, nextPrime(Z, Next), !.
nextPrime(X, Next):- Next is X, !.


add_min_prime(X, Min_prime) :- min_prime(X, _), !.
add_min_prime(X, Min_prime) :- assertz(min_prime(X, Min_prime)), !.

siftUp(Cur, Dx, Max, Min_prime) :- 
	Cur =< Max, 
	assertz(setOfComps(Cur)), 
	add_min_prime(Cur, Min_prime),
	Next is Cur + Dx, siftUp(Next, Dx, Max, Min_prime).

try_sift_up(CurPrime, Max):- CurPrime*CurPrime > Max, !.
try_sift_up(CurPrime, Max):-
	Start is CurPrime * CurPrime,
	not siftUp(Start, CurPrime, Max, CurPrime), !.


buildErat(CurPrime, Max):- 
	CurPrime =< Max,
	assertz(min_prime(CurPrime, CurPrime)),
	try_sift_up(CurPrime, Max),
	N1 is CurPrime + 1,
	nextPrime(N1, NextPrime), 
	buildErat(NextPrime, Max).
	
init(X) :- not buildErat(2, X).

prime(N) :- not setOfComps(N).
composite(N) :- N > 2, not prime(N).

calculate_number(X, [H|T], Prev, Ans):-
	Prev =< H,
	prime(H),
	Next_x is X*H,
	calculate_number(Next_x, T, H, Ans), !.
calculate_number(X, [], _, Ans):-Ans is X, !.


calculate_list(X, List, Ans):-
	min_prime(X, Min_prime),
	Rest is X / Min_prime,
	append(List, [Min_prime], Next_list),
	calculate_list(Rest, Next_list, Ans).
calculate_list(1, List, List):- !.
	
prime_divisors(X, List):- list(List), calculate_number(1, List, 1, X), !.
prime_divisors(X, List):- integer(X), calculate_list(X, [], List), !.

get_lcm(L, [], L):- !.
get_lcm([], L, L):- !.
get_lcm([H|Ta], [H|Tb], [H|Ans]):- get_lcm(Ta, Tb, Ans), !.
get_lcm([Ha|Ta], [Hb|Tb], [Ha|Ans]):- Ha < Hb, get_lcm(Ta, [Hb|Tb], Ans), !.
get_lcm([Ha|Ta], [Hb|Tb], [Hb|Ans]):- Ha > Hb, get_lcm([Ha|Ta], Tb, Ans), !.
	

lcm(A, B, Lcm):-
	prime_divisors(A, List_a),
	prime_divisors(B, List_b),
	get_lcm(List_a, List_b, Lcm_list), 
	prime_divisors(Lcm, Lcm_list), !.