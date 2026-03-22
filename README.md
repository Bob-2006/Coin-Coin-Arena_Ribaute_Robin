# Coin-Coin-Arena_Ribaute_Robin
Projet coin coin - Simulateur de combats de canards en plastique.

Ce dépôt contient l'implémentation du Projet 1 : **Coin-Coin Arena**, réalisé dans le cadre du module de Conception Orientée Objet (Concepts avancés)[cite: 1]. 

## État d'avancement
Ce fichier documente le développement de la **Partie A (Modélisation par héritage)** , implémentée dans son intégralité jusqu'à la section **A.5 (Programme principal)**.

## Architecture et Fonctionnalités Implémentées

### A.1 - La classe abstraite `CanardDeCombat`
La fondation de notre modèle orienté objet. Cette classe abstraite centralise les caractéristiques communes de tous les combattants:
* **Attributs** : Nom d'espèce, surnom, points de vie (max et actuels), et points d'attaque.
* **Comportements de base** : Subir des dégâts, vérifier l'état KO (`estKO()`), et se soigner.
* **Préparation au combat** : Une méthode de combat `attaquer(CanardDeCombat cible)` qui calcule les dégâts et gère le déroulement de l'attaque.

### A.2 - Les types, l'héritage et le Double Dispatch
Quatre sous-classes concrètes ont été créées, chacune représentant un type élémentaire avec ses spécificités:
* **CanardFeu** (Type Feu) : Multiplie ses dégâts de base grâce à un attribut spécifique `intensiteFlamme`
* **CanardEau** (Type Eau) : Personnalise son attaque avec un message affichant sa `pressionJet`.
* **CanardPlante** (Type Plante) : Possède une méthode `regenerer()` lui permettant de restaurer 10% de ses PV maximums à chaque tour.
* **CanardClassique** (Type Normal) : Le canard de base standard, sans particularité.

**L'astuce d'architecture : le Double Dispatch**
La résolution des forces et faiblesses (la table des multiplicateurs de dégâts) a été implémentée **sans utiliser de `if/else` sur les types ni de comparaisons de chaînes de caractères**. 
Le projet repose sur le polymorphisme pur en inversant l'appel (Double Dispatch) : l'attaquant s'identifie via `this` auprès de la cible lors de l'attaque, et la cible résout dynamiquement le bon multiplicateur selon son propre type et celui de l'attaquant via la méthode `etreAttaqueePar()`.

### A.3 - Les Interfaces et le Canard Confus
Pour garantir une bonne évolutivité du code et ne pas le restreindre aux seuls "canards", des interfaces ont été ajoutées:
* **`Soignable`** : Définit le contrat pour le soin (`soigner()`, `getPvActuels()`, `getPvMax()`), incluant une méthode `default` pour calculer le pourcentage de PV .
* **`Combattant`** : Définit le contrat pour l'arène (`attaquer()`, `estKO()`, `getNom()`) .

**Le Canard Confus (A.3.3) :**
Une sous-classe spéciale qui étend le `CanardEau` pour y introduire une part d'imprévisibilité et de comportements modifiés:
* **Confusion** : Lorsqu'il attaque, ce canard a 1 chance sur 4 d'être confus et de s'infliger des dégâts à lui-même.
* **Mécanique de Migraine** : Il possède une méthode `migraine()` qui le rend temporairement "enragé", doublant sa puissance d'attaque pour le coup suivant.

### A.4 - L'équipe et l'arène
La gestion des affrontements a été structurée autour de deux classes clés:
* **`Equipe`** : Gère un tableau contenant jusqu'à 6 `CanardDeCombat` et le nom de leur dresseur. Elle offre des méthodes pour ajouter ou retirer des membres, soigner toute l'équipe, et vérifier si tous les canards sont KO.
* **`Arene`** : Simule le combat au tour par tour entre deux équipes. Chaque canard combat jusqu'à être KO, déclenchant l'envoi du canard suivant, et les canards Plante bénéficient de leur régénération en fin de tour.

### A.5 - Programme principal
La classe `Main` orchestre une démonstration complète:
1. Création de deux équipes distinctes avec des canards variés, incluant un `CanardConfus`.
2. Affichage détaillé des équipes.
3. Lancement d'un combat complet dans l'arène avec un affichage clair du déroulement.
4. Affichage du compteur global via `CanardDeCombat.getNbCanardsCrees()`.
