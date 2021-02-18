package za.co.wethinkcode.mastermind;



public class Mastermind {
    private final String code;
    private final Player player;
    private int correctSamePosition = 0;
    private int correctWrongPosition = 0;

//    String code = "1234";


    public Mastermind(CodeGenerator generator, Player player){
        this.code = generator.generateCode();
        this.player = player;
        System.out.println("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.");
    }



    public Mastermind(){
        this(new CodeGenerator(), new Player());
    }



    public void runGame(){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      WHILE COMPARING STRINGS use object.equals() (Credit IntelliJ)                                                  /
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println(this.code);

        int turns = player.turns;
//        System.out.println(turns);
//        String user_in = player.getGuess();
        while ( true ){
            String user_in = player.getGuess();


            if ( turns == 0) {
                System.out.printf("Number of correct digits in correct place: %d\n",correctSamePosition);
                System.out.printf("Number of correct digits not in correct place: %d\n",correctWrongPosition);
                System.out.println("No more turns left.");
                System.out.printf("The code was: %s",this.code);
                break;
            }
//            System.out.println(turns);
//            user_in = player.getGuess();


//            int i = 0;

            for (int i = 0 ; i < 4; i++){
                if ( user_in.charAt(i) == this.code.charAt(i)){correctSamePosition++;}
                else if (this.code.indexOf(user_in.charAt(i)) > 0){correctWrongPosition++;}
                }




            if (user_in.equals(this.code)){
                System.out.printf("Number of correct digits in correct place: %d\n",correctSamePosition);
                System.out.printf("Number of correct digits not in correct place: %d\n",correctWrongPosition);
                System.out.println("Congratulations! You are a codebreaker!");
                System.out.printf("The code was: %s",this.code);
                break;
            }
            else if (user_in.equalsIgnoreCase("exit") || user_in.equalsIgnoreCase("quit")){
                System.exit(999);
            }
            else if ( turns > -2) {
                System.out.printf("Number of correct digits in correct place: %d\n",correctSamePosition);
                System.out.printf("Number of correct digits not in correct place: %d\n",correctWrongPosition);
                System.out.printf("Turns left: %s\n", turns);
                turns--;


            }

            correctSamePosition = 0;
            correctWrongPosition = 0;

        }




    }

    public static void main(String[] args){
        Mastermind game = new Mastermind();
        game.runGame();
    }
}
