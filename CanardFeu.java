public class CanardFeu extends CanardDeCombat {
    private double intensiteFlamme;

    // Constructeur complet
    public CanardFeu(String nom, int pvMax, int atk, double intensiteFlamme) {
        super(nom, pvMax, atk);
        this.intensiteFlamme = Math.max(0.8, Math.min(1.5, intensiteFlamme)); // Contraint entre 0.8 et 1.5
    }

    // Constructeur simplifié (chaîné)
    public CanardFeu(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 1.0);
    }

    @Override
    public String getType() {
        return "Feu";
    }

    @Override
    public int getAtk() {
        // Multiplie ses dégâts par intensiteFlamme
        return (int) (super.getAtk() * intensiteFlamme);
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this); // this est reconnu comme CanardFeu
        effectuerAttaque(cible, mult);
    }

    // En tant que CIBLE : redéfinition des multiplicateurs
    @Override
    public double etreAttaqueePar(CanardFeu attaquant) { return 0.5; }
    
    @Override
    public double etreAttaqueePar(CanardEau attaquant) { return 2.0; }
    
    @Override
    public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; }
    
    // Pas besoin de redéfinir CanardClassique, la valeur par défaut est 1.0 dans la classe mère

    @Override
    public String toString() {
        return super.toString() + " [Intensité Flamme: " + intensiteFlamme + "]";
    }
}