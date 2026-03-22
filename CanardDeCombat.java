public abstract class CanardDeCombat implements Soignable, Combattant {
    
    // --- Attributs statiques ---
    private static int nbCanardsCrees = 0; // Compteur global [cite: 28]
    public static final int ATK_MIN = 1;   // Constante [cite: 28]

    // --- Attributs d'instance ---
    private final String nom;      // Nom d'espèce (ex: "Canard Flamme") [cite: 24]
    private String surnom;         // Surnom donné par le dresseur [cite: 24]
    private final int pvMax;       // Points de vie maximum [cite: 24]
    private int pvActuels;         // Points de vie courants [cite: 25]
    private int atk;               // Puissance d'attaque de base [cite: 26]

    // --- Constructeur ---
    public CanardDeCombat(String nom, int pvMax, int atk) {
        // Validation des paramètres [cite: 31]
        if (pvMax <= 0) {
            throw new IllegalArgumentException("Les PV max doivent être > 0");
        }
        if (atk < ATK_MIN) { 
            throw new IllegalArgumentException("L'attaque doit être au moins de " + ATK_MIN);
        }
        
        this.nom = nom;
        this.surnom = nom; // Surnom par défaut = nom [cite: 30]
        this.pvMax = pvMax;
        this.pvActuels = pvMax; // Initialisés au max [cite: 30]
        this.atk = atk;
        
        nbCanardsCrees++; // Incrémente le compteur global [cite: 30]
    }

    // --- Getters & Setters --- [cite: 33]
    public String getNom() { return nom; }
    public String getSurnom() { return surnom; }
    public void setSurnom(String surnom) { this.surnom = surnom; }
    public int getPvMax() { return pvMax; }
    public int getPvActuels() { return pvActuels; }
    public int getAtk() { return atk; }
    
    public static int getNbCanardsCrees() { return nbCanardsCrees; }

    // --- Méthodes de base (Soin et Dégâts) ---
    public boolean estKO() {
        return pvActuels <= 0; // [cite: 34]
    }

    public void subirDegats(int degats) {
        this.pvActuels -= degats;
        if (this.pvActuels < 0) {
            this.pvActuels = 0; // Minimum 0 [cite: 35]
        }
        // Affichage du message [cite: 35, 36]
        System.out.println(surnom + " subit " + degats + " dégâts (PV: " + pvActuels + "/" + pvMax + ")");
    }

    public void soigner() {
        this.pvActuels = this.pvMax; // Restaure au maximum [cite: 37]
    }

    @Override
    public String toString() {
        // Format attendu [cite: 38]
        // Utilisation de getAtk() au lieu de l'attribut atk pour gérer les modificateurs comme la migraine ou l'intensité de flamme
        return "[" + getType() + "] " + nom + " «" + surnom + "» (PV: " + pvActuels + "/" + pvMax + " | ATK: " + getAtk() + ")";
    }

    // --- Méthodes abstraites ---
    public abstract String getType(); // [cite: 40]
    public abstract void attaquer(CanardDeCombat cible); // Abstraite pour le double dispatch [cite: 116, 117]

    // --- Méthodes de réception (Double Dispatch) --- 
    // Par défaut, le multiplicateur est de 1.0 [cite: 94-101]
    public double etreAttaqueePar(CanardFeu attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardEau attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardPlante attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardClassique attaquant) { return 1.0; }

    // --- Logique d'attaque commune ---
    protected void effectuerAttaque(CanardDeCombat cible, double mult) {
        int degats = (int) (getAtk() * mult); // [cite: 107, 108]
        System.out.println(getSurnom() + " attaque " + cible.getSurnom() + 
                           " ! (" + getType() + " -> " + cible.getType() + 
                           " : x" + mult + ") -> " + degats + " dégâts"); // [cite: 109-113]
        cible.subirDegats(degats); // [cite: 114]
    }
}