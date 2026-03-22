public interface Soignable {
    void soigner();
    int getPvActuels();
    int getPvMax();
    
    default double pourcentagePV() {
        return (double) getPvActuels() / getPvMax() * 100;
    }
}