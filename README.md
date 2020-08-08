# dbscan
la segmentation par voisinage dense est un algorithme de partitionnement des données proposé en 1996 par Martin Ester, Hans-Peter Kriegel, Jörg Sander et Xiaowei Xu. On l’appel DBSCAN aussi.[1]
Comme le nom indique, cet algorithme ce base sur l’idée que les éléments en relation (appartiennent au même segment) constituent une densité important, donc par conséquent, il sont séparées par des régions moins denses. les éléments qui ne constituent pas une densité importante, on les appels des
points bruits.

# l'algorithme à les éléments clefs suivants:
● Epsilon (ou bien rayon): une distance qu’on spécifie, elle représente le rayon où on peut chercher les voisins d’un point donné.

● Minimum (m): une valeur qu’on spécifiée, elle représente le nombre minimal de voisins qu’on doit avoir pour classifier un point comme core.

● un point core: un point qui a plus de m voisins.

● un point frontière: un point voisin d’un point core.

● un point bruit: un point qui n’est pas au voisinage d’un point core, et qui n’a pas m> de voisins.

● accessibilité: on dit que b est accessible par a, et on note a>b, si b est un voisin de a (la distance entre a et b est inférieure à epsilon.

● connectivité: on dit que a et b sont connectés si on a: a>p1>p2>...>pn>b 

# Comment est cet algorithme différent:
on comparant cet algorithme a K-means, on trouve les différences suivantes:

● Contrairement à K-means, DBSCAN n’a pas besoin de segmenter tous les points, les points qui sont loins des autres sont classifiés comme bruit.

● K-means a besoin de savoir à combien on veut séparer nos données, DBSCAN non.

● DBSCAN a une meilleur habilité de détecter des grappes de formes aléatoires, comme on voit dans la figure ci dessous.

# L’algorithme:
étape 1: prendre un point non visité classifié (n’appartient à aucun segment) hasard, déterminer si c’est un point core (nombre de voisins supérieur au minimum), si non on le classifie comme bruit.

étape 2: dès qu’on trouve un point core, on ajoute tous les points accessibles par ce dernier au même segment. on fait la même chose pour tous les points accessibles par les voisins du core.

étape 3: on répète ces étapes jusqu’à tous les points sont classifiés comme segments ou bruits.

# pour calculer la distance entre le mot on utiliser Algorithme de levenshtein:
cette algorithme nous permet de calculer la distance entre deux chaînes de caractères, des mots, noms etc mais pas des phrases. Cette fonction prends deux chaînes de caractères, elle retourne 0 si ils sont identique, si non le nombre minimum de modifications (ajout, suppression ou substitution
d’un caractère ) requis pour les rendre identiques.

# exemple:
la distance entre HONDA et HYUNDAI est égale à 3. 
avec HYUNDAI on supprime Y, on substitue U par O, finalement on supprime I. 
ou bien, avec HONDA, on ajoute Y, on substitue O par U, on ajoute I.

# Sources:

Pseudocode of the DBSCAN algorithm 
https://www.researchgate.net/figure/Pseudocode-of-the-DBSCAN-algorithm_fig2_325059373

Algorithme de levenshtein
https://www.youtube.com/watch?v=Ah_4xqvS1WU
