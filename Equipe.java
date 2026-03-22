public class Equipe {
    private static int nbEquipesCrees = 0; // [cite: 206]
    
    private final String nomDresseur; // [cite: 205]
    private final CanardDeCombat[] canards; // [cite: 204]
    private int nbCanards;

    public Equipe(String nomDresseur) {
        this.nomDresseur = nomDresseur;
        this.canards = new CanardDeCombat[6]; // Taille max 6 [cite: 204]
        this.nbCanards = 0;
        nbEquipesCrees++;
    }

    public String getNomDresseur() { return nomDresseur; }
    public static int getNbEquipesCrees() { return nbEquipesCrees; }

    public boolean ajouter(CanardDeCombat c) {
        if (nbCanards < 6) { // [cite: 207]
            canards[nbCanards] = c;
            nbCanards++;
            return true;
        }
        return false;
    }

    public void retirer(String surnom) {
        for (int i = 0; i < nbCanards; i++) {
            if (canards[i].getSurnom().equals(surnom)) {
                // Décalage pour combler le trou [cite: 207]
                for (int j = i; j < nbCanards - 1; j++) {
                    canards[j] = canards[j + 1];
                }
                canards[nbCanards - 1] = null;
                nbCanards--;
                break;
            }
        }
    }

    public CanardDeCombat getPremierValide() {
        for (int i = 0; i < nbCanards; i++) {
            if (!canards[i].estKO()) {
                return canards[i]; // Renvoie le premier non-KO [cite: 207]
            }
        }
        return null; // Tous KO [cite: 207]
    }

    public void soignerTous() {
        for (int i = 0; i < nbCanards; i++) {
            canards[i].soigner(); // Via l'interface Soignable [cite: 207]
        }
    }

    public boolean touteKO() {
        return getPremierValide() == null; // [cite: 207]
    }

    public void afficher() {
        System.out.println("=== ÉQUIPE de " + nomDresseur + " ==="); // [cite: 207]
        for (int i = 0; i < nbCanards; i++) {
            System.out.println(canards[i].toString());
        }
    }
}