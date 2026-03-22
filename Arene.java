public class Arene {

    public void combattre(Equipe e1, Equipe e2) {
        System.out.println("\n=== COMBAT ==="); // [cite: 241]
        
        CanardDeCombat c1 = e1.getPremierValide(); // [cite: 211]
        CanardDeCombat c2 = e2.getPremierValide(); // [cite: 211]
        
        int tour = 1;

        // Le combat continue tant qu'aucune équipe n'est entièrement KO [cite: 216]
        while (c1 != null && c2 != null) {
            System.out.println("\nTour " + tour + " :"); // [cite: 242]
            
            // 1. Attaque de l'équipe 1 [cite: 212]
            c1.attaquer(c2);
            
            // Si la cible est KO, on gère l'envoi du suivant [cite: 213]
            if (c2.estKO()) {
                System.out.println(c2.getSurnom() + " est KO !"); // [cite: 250]
                c2 = e2.getPremierValide();
                if (c2 != null) {
                    System.out.println(e2.getNomDresseur() + " envoie " + c2.getSurnom() + " !"); // [cite: 251]
                }
            } else {
                // 2. Attaque de l'équipe 2 (si le canard n'est pas KO) [cite: 214]
                c2.attaquer(c1);
                
                if (c1.estKO()) {
                    System.out.println(c1.getSurnom() + " est KO !"); // [cite: 250]
                    c1 = e1.getPremierValide();
                    if (c1 != null) {
                        System.out.println(e1.getNomDresseur() + " envoie " + c1.getSurnom() + " !"); // [cite: 251]
                    }
                }
            }

            // 3. Fin de tour : Régénération des canards Plante [cite: 215]
            if (c1 != null && !c1.estKO() && c1 instanceof CanardPlante) { // [cite: 218]
                ((CanardPlante) c1).regenerer();
            }
            if (c2 != null && !c2.estKO() && c2 instanceof CanardPlante) { // [cite: 218]
                ((CanardPlante) c2).regenerer();
            }

            tour++;
        }

        // 4. Récapitulatif de fin de combat [cite: 217]
        System.out.println("\n=== FIN DU COMBAT ===");
        if (e1.touteKO()) {
            System.out.println("Vainqueur : Équipe de " + e2.getNomDresseur() + " en " + (tour - 1) + " tours !"); // [cite: 217]
        } else {
            System.out.println("Vainqueur : Équipe de " + e1.getNomDresseur() + " en " + (tour - 1) + " tours !"); // [cite: 217]
        }
    }
}