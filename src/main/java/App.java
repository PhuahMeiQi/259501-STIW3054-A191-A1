public class App
{
    public void app() {
        try{
            saveToExcel save = new saveToExcel();
            //saveExcel save1 = new saveExcel();
            save.saveData();
           // save1.saveData();

            System.out.println("\n\nSaving data to Excel...");
            Thread.sleep(2000);// wait for 2 second
            System.out.println("Excel Written Successfully");
            System.out.println("END");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }//End Main
}