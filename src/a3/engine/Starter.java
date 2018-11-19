package a3.engine;

public class Starter {
    public Starter(){
        Engine eng = new Engine();
        eng.start();
    }
    public static void main(String [] args){
        new Starter();
    }
}
