# Coin-Coin-Arena_Ribaute_Robin
[cite_start]Projet coin coin - Simulateur de combats de canards en plastique[cite: 9, 10].

[cite_start]Ce dépôt contient l'implémentation du Projet 1 : **Coin-Coin Arena**, réalisé dans le cadre du module de Conception Orientée Objet (Concepts avancés)[cite: 1]. 

## État d'avancement
[cite_start]Ce fichier documente le développement de la **Partie A (Modélisation par héritage)** [cite: 12][cite_start], implémentée jusqu'à la section **A.3.3 (Le Canard Confus)**[cite: 187].

## Architecture et Fonctionnalités Implémentées

### A.1 - La classe abstraite `CanardDeCombat`
La fondation de notre modèle orienté objet. [cite_start]Cette classe abstraite centralise les caractéristiques communes de tous les combattants[cite: 21, 22]:
* [cite_start]**Attributs** : Nom d'espèce, surnom, points de vie (max et actuels), et points d'attaque[cite: 24, 25, 26].
* [cite_start]**Comportements de base** : Subir des dégâts, vérifier l'état KO (`estKO()`), et se soigner[cite: 34, 35, 37].
* [cite_start]**Préparation au combat** : Une méthode de combat `attaquer(CanardDeCombat cible)` qui calcule les dégâts et gère le déroulement de l'attaque[cite: 41, 42].

### A.2 - Les types, l'héritage et le Double Dispatch
[cite_start]Quatre sous-classes concrètes ont été créées, chacune représentant un type élémentaire avec ses spécificités[cite: 51, 52]:
* [cite_start] **CanardFeu** (Type Feu) : Multiplie ses dégâts de base grâce à un attribut spécifique `intensiteFlamme`[cite: 52].
* [cite_start] **CanardEau** (Type Eau) : Personnalise son attaque avec un message affichant sa `pressionJet`[cite: 52].
* [cite_start] **CanardPlante** (Type Plante) : Possède une méthode `regenerer()` lui permettant de restaurer 10% de ses PV maximums à chaque tour[cite: 52].
* [cite_start] **CanardClassique** (Type Normal) : Le canard de base standard, sans particularité[cite: 52].

**L'astuce d'architecture : le Double Dispatch**
[cite_start]La résolution des forces et faiblesses (la table des multiplicateurs de dégâts) a été implémentée **sans utiliser de `if/else` sur les types ni de comparaisons de chaînes de caractères**[cite: 61]. 
[cite_start]Le projet repose sur le polymorphisme pur en inversant l'appel (Double Dispatch) : l'attaquant s'identifie via `this` auprès de la cible lors de l'attaque, et la cible résout dynamiquement le bon multiplicateur selon son propre type et celui de l'attaquant via la méthode `etreAttaqueePar()`[cite: 89, 90, 91, 158, 159].

### A.3 - Les Interfaces et le Canard Confus (Jusqu'à A.3.3)
[cite_start]Pour garantir une bonne évolutivité du code et ne pas le restreindre aux seuls "canards", des interfaces ont été ajoutées[cite: 166, 177, 185]:
* [cite_start]**`Soignable`** : Définit le contrat pour le soin (`soigner()`, `getPvActuels()`, `getPvMax()`), incluant une méthode `default` pour calculer le pourcentage de PV [cite: 167-175].
* [cite_start]**`Combattant`** : Définit le contrat pour l'arène (`attaquer()`, `estKO()`, `getNom()`) [cite: 178-182].

**Le Canard Confus (A.3.3) :**
[cite_start]Une sous-classe spéciale qui étend le `CanardEau` pour y introduire une part d'imprévisibilité et de comportements modifiés[cite: 188]:
* [cite_start]**Confusion** : Lorsqu'il attaque, ce canard a 1 chance sur 4 d'être confus et de s'infliger des dégâts à lui-même[cite: 190].
* [cite_start]**Mécanique de Migraine** : Il possède une méthode `migraine()` qui le rend temporairement "enragé", doublant sa puissance d'attaque pour le coup suivant[cite: 192, 193].
