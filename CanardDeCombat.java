public abstract class CanardDeCombat implements Soignable, Combattant {
    // Attributs statiques
    private static int nbCanardsCrees = 0;
    public static final int ATK_MIN = 1;

    // Attributs d'instance
    private final String nom;
    private String surnom;
    private final int pvMax;
    private int pvActuels;
    private int atk;

    // Constructeur
    public CanardDeCombat(String nom, int pvMax, int atk) {
        if (pvMax <= 0) throw new IllegalArgumentException("Les PV max doivent être > 0");
        if (atk < ATK_MIN) throw new IllegalArgumentException("L'attaque doit être au moins de " + ATK_MIN);
        
        this.nom = nom;
        this.surnom = nom; // Surnom par défaut
        this.pvMax = pvMax;
        this.pvActuels = pvMax;
        this.atk = atk;
        nbCanardsCrees++;
    }

    // --- Getters & Setters ---
    public String getNom() { return nom; }
    public String getSurnom() { return surnom; }
    public void setSurnom(String surnom) { this.surnom = surnom; }
    public int getPvMax() { return pvMax; }
    public int getPvActuels() { return pvActuels; }
    public int getAtk() { return atk; }
    public static int getNbCanardsCrees() { return nbCanardsCrees; }

    // --- Méthodes de combat et de soin ---
    public boolean estKO() {
        return pvActuels <= 0;
    }

    public void subirDegats(int degats) {
        this.pvActuels = Math.max(0, this.pvActuels - degats);
        System.out.println(surnom + " subit " + degats + " dégâts (PV: " + pvActuels + "/" + pvMax + ")");
    }

    public void soigner() {
        this.pvActuels = this.pvMax;
    }

    @Override
    public String toString() {
        return "[" + getType() + "] " + nom + " «" + surnom + "» (PV: " + pvActuels + "/" + pvMax + " | ATK: " + atk + ")";
    }

    // --- Méthode abstraite ---
    public abstract String getType();
    public abstract void attaquer(CanardDeCombat cible);

    // --- Double Dispatch : Méthodes de réception par défaut ---
    public double etreAttaqueePar(CanardFeu attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardEau attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardPlante attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardClassique attaquant) { return 1.0; }

    // --- Logique d'attaque commune ---
    protected void effectuerAttaque(CanardDeCombat cible, double mult) {
        int degats = (int) (getAtk() * mult);
        System.out.println(getSurnom() + " attaque " + cible.getSurnom() + 
                           " ! (" + getType() + " -> " + cible.getType() + " : x" + mult + ") -> " + degats + " dégâts");
        cible.subirDegats(degats);
    }
}