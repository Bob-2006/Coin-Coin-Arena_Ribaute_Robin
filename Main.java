public class Main {
    public static void main(String[] args) {
        // 1. Création de deux équipes [cite: 227]
        Equipe equipeSacha = new Equipe("Sacha");
        Equipe equipeOndine = new Equipe("Ondine");

        // Création des canards pour Sacha
        CanardFeu gerard = new CanardFeu("Canard Flamme", 39, 52);
        gerard.setSurnom("Gérard");
        
        // 2. Inclut au moins un CanardConfus [cite: 228]
        CanardConfus coinCoin = new CanardConfus("Canard Confus", 52, 48);
        coinCoin.setSurnom("Coin-Coin");
        
        CanardPlante fernand = new CanardPlante("Canard Mousse", 45, 49);
        fernand.setSurnom("Fernand");

        equipeSacha.ajouter(gerard);
        equipeSacha.ajouter(coinCoin);
        equipeSacha.ajouter(fernand);

        // Création des canards pour Ondine
        CanardEau hubert = new CanardEau("Canard Marin", 44, 48);
        hubert.setSurnom("Hubert");
        
        CanardClassique marcel = new CanardClassique("Canard Classique", 50, 45);
        marcel.setSurnom("Marcel");
        
        CanardFeu josette = new CanardFeu("Canard Braise", 42, 55);
        josette.setSurnom("Josette");

        equipeOndine.ajouter(hubert);
        equipeOndine.ajouter(marcel);
        equipeOndine.ajouter(josette);

        // 3. Affiche les deux équipes [cite: 229]
        equipeSacha.afficher();
        System.out.println();
        equipeOndine.afficher();

        // 4. Lance un combat et affiche le déroulement tour par tour [cite: 230]
        Arene arene = new Arene();
        arene.combattre(equipeSacha, equipeOndine);

        // 5. Affiche le nombre total de canards créés [cite: 231]
        System.out.println("\nStatistique globale :");
        System.out.println("Nombre total de canards créés dans le monde : " + CanardDeCombat.getNbCanardsCrees());
    }
}