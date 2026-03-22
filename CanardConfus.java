public class CanardConfus extends CanardEau { [cite: 188]
    private boolean enrage = false; // Utilisé pour la migraine [cite: 193]

    // Constructeur complet
    public CanardConfus(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk, pressionJet);
    }

    // Constructeur simplifié
    public CanardConfus(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    // --- Méthode spécifique : La migraine ---
    public void migraine() {
        System.out.println(getSurnom() + " se tient la tête... COIN. COIN."); [cite: 192]
        enrage = true; // Active le doublement de l'attaque pour le prochain coup [cite: 193]
    }

    // On modifie l'attaque de base si le canard est enragé [cite: 193]
    @Override
    public int getAtk() {
        int baseAtk = super.getAtk();
        if (enrage) {
            return baseAtk * 2; [cite: 193]
        }
        return baseAtk;
    }

    // --- Le comportement d'attaque confus ---
    @Override
    public void attaquer(CanardDeCombat cible) {
        // 1 chance sur 4 (25%) d'être confus [cite: 190]
        if (Math.random() < 0.25) {
            System.out.println(getSurnom() + " est confus ! Il se cogne la tête... Coin coin ?"); [cite: 191]
            
            // Il s'attaque lui-même ! [cite: 190] 
            // On calcule le multiplicateur contre lui-même (type Eau contre type Eau)
            double mult = this.etreAttaqueePar(this);
            effectuerAttaque(this, mult);
        } else {
            // Les 3/4 du temps, il attaque normalement la cible [cite: 191]
            super.attaquer(cible); 
        }
        
        // Après avoir attaqué (lui-même ou l'ennemi), la migraine redescend
        enrage = false;
    }

    // --- Redéfinition du toString ---
    @Override
    public String toString() {
        return super.toString() + " ??"; // Ajoute ?? à la fin [cite: 189]
    }
}