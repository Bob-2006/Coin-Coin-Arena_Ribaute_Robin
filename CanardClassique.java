public class CanardClassique extends CanardDeCombat {

    // Constructeur complet
    public CanardClassique(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    // Constructeur simplifié (chaîné)
    public CanardClassique(String nom) {
        this(nom, 50, 45);
    }

    @Override
    public String getType() {
        return "Normal";
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        double mult = cible.etreAttaqueePar(this); // Double dispatch : this est CanardClassique
        effectuerAttaque(cible, mult);
    }

    // En tant que CIBLE :
    // Aucune redéfinition nécessaire. Le type Normal prend des dégâts x1.0 de tous les types.
    // Les méthodes de CanardDeCombat (qui renvoient 1.0) font parfaitement l'affaire.
}