:-include('symptom-additionalExam.pl').
:-include('diagnosis-medication.pl').
:-include('symptom-diagnosis.pl').




%prikaz liste lekova za odredjenu dijagnozu
lekovi(L,B) :- findall([V,A],lek(B,A,V),L2),sort(L2,L).


%postoji li odredjeni lijek za tu bolest u listi
postoji(M,B) :- lekovi(L,B),pripada(M,L).

%za koje sve bolesti pomaze taj lijek
za_koje_bolesti(M,L) :- findall(B,lek(B,M),L).

mymember(X,[X|_]).
mymember(X,[_|Ys]) :- mymember(X,Ys).

%lek(svrab,a,5).
%lek(svrab,b,3).
%lek(svrab,c,2).
%lek(kontaktni_dermatitis,a,4).
%lek(kontaktni_dermatitis,c,2).
%lek(kontaktni_dermatitis,b,6).

isti_lijek([],[]).
isti_lijek([H|T],L3) :- findall([V,C],lek(C,H,V),L1),
isti_lijek(T,L2),pripada(L1,L2,L3).

isti_lijek_sortirano([],[]).
isti_lijek_sortirano(L,L3) :- isti_lijek(L,L2),sort(L2,L3).
%128
isti_jedan_lijek(A,L) :- findall([C,B],lek(C,A,B),L).
isti_jedan_lijek_sortirano(A,L) :- findall([C,B],lek(C,A,B),L1),sort(L1,L).

dijagnoze_preko_simptoma([],[]).
dijagnoze_preko_simptoma([H|T],L3) :- findall([C,B],dijagnoza(H,C,B),L1),
dijagnoze_preko_simptoma(T,L2),pripada(L1,L2,L3).

dijagnoza_preko_jednog_simptoma(L5,B5) :- findall([V5,A5],dijagnoza(B5,A5,V5),L6),sort(L6,L5).


ispitivanja_preko_simptoma([],[]).
ispitivanja_preko_simptoma([H|T],L9) :- findall([C,B],dijagnoza(H,C,B),L10),
ispitivanja_preko_simptoma(T,L11),pripada(L10,L11,L9).

%sva ispitivanja za simptom
ispitivanja_preko_jednog_simptoma(L7,B7) :- findall([V7,A7],dodatni_pregled(B7,A7,V7),L8),sort(L8,L7).


pripada([],L,[]).
pripada([H|T],L,L3) :- mymember(H,L) ->pripada(T,L,L2),append([H],L2,L3)
; pripada(T,L,L2).


verovatnoca_za_dijagnozu(S, L) :-findall([V,B], dijagnoza(S,B,V), L1), sort(L1,L).


