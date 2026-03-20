public class CanardPlante extends CanardDeCombat {

    // Constructeur complet
    public CanardPlante(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    // Constructeur simplifié (chaîné) avec des valeurs par défaut
    public CanardPlante(String nom) {
        this(nom, 45, 49); 
    }

    @Override
    public String getType() {
        return "Plante";
    }

    public void regenerer() {
        if (!estKO()) {
            int soin = (int) (getPvMax() * 0.10); // Restaure 10% des PV max
            // On suppose qu'une méthode soigner(int) existe, ou on simule le soin :
            // this.soigner(soin); 
            System.out.println(getSurnom() + " se régénère et récupère " + soin + " PV !");
        }
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this); // Double dispatch : this est CanardPlante
        effectuerAttaque(cible, mult);
    }

    // --- Table des types : Multiplicateurs en tant que CIBLE ---
    // Un CanardPlante est faible face au Feu (2.0), mais résiste à l'Eau (0.5) et à la Plante (0.5)
    
    @Override
    public double etreAttaqueePar(CanardFeu attaquant) { return 2.0; }

    @Override
    public double etreAttaqueePar(CanardEau attaquant) { return 0.5; }

    @Override
    public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; }

    @Override
    public String toString() {
        return super.toString() + " [Régénérant]";
    }
}