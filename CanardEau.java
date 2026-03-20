public class CanardEau extends CanardDeCombat {
    private int pressionJet;

    // Constructeur complet
    public CanardEau(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk);
        this.pressionJet = pressionJet;
    }

    // Constructeur simplifié (chaîné)
    public CanardEau(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 10); // Pression par défaut de 10
    }

    @Override
    public String getType() {
        return "Eau";
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        System.out.println("Jet d'eau (pression: " + pressionJet + ") !");
        double mult = cible.etreAttaqueePar(this); // Double dispatch : this est CanardEau
        effectuerAttaque(cible, mult);
    }

    // --- Table des types : Multiplicateurs en tant que CIBLE ---
    // Un CanardEau résiste au Feu (0.5) et à l'Eau (0.5), mais est faible face à la Plante (2.0)
    
    @Override
    public double etreAttaqueePar(CanardFeu attaquant) { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardEau attaquant) { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardPlante attaquant) { return 2.0; }

    @Override
    public String toString() {
        return super.toString() + " [Pression: " + pressionJet + "]";
    }
}S