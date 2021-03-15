import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.io.File;

public class CarSharingTest extends StageTest<Void> {

    private static final String databaseFileName = "src/carsharing/db/carsharing.mv.db";
    private static DatabaseUtil db = new DatabaseUtil();

    @DynamicTestingMethod
    public CheckResult test1_testMenu() {
        TestedProgram program = new TestedProgram();
        String output = program.start("-databaseFileName", "carsharing");

        if (!output.contains("1. Log in as a manager")) {
            return CheckResult.wrong("Start menu should contain \"1. Log in as a manager\"");
        }

        if (!output.contains("0. Exit")) {
            return CheckResult.wrong("Start menu should contain \"0. Exit\"");
        }

        output = program.execute("1");

        if (!output.contains("1. Company list")) {
            return CheckResult.wrong("After choosing 'Log in as a manager' item you should print menu that contains '1. Company list' item");
        }

        if (!output.contains("2. Create a company")) {
            return CheckResult.wrong("After choosing 'Log in as a manager' item you should print menu that contains '2. Create a company' item");
        }

        if (!output.contains("0. Back")) {
            return CheckResult.wrong("After choosing 'Log in as a manager' item you should print menu that contains '0. Back' item");
        }

        output = program.execute("0");

        if (!output.contains("1. Log in as a manager")) {
            return CheckResult.wrong("After choosing '0. Back' item you should print previous menu and it should contain \"1. Log in as a manager\"");
        }

        if (!output.contains("0. Exit")) {
            return CheckResult.wrong("After choosing '0. Back' item you should print previous menu and it should contain \"0. Exit\"");
        }

        return CheckResult.correct();
    }

    @DynamicTestingMethod
    public CheckResult test2_ifDatabaseExist() {

        TestedProgram program = new TestedProgram();
        program.start("-databaseFileName", "carsharing");
        program.execute("0");

        if (!program.isFinished()) {
            return CheckResult.wrong("After choosing 'Exit' item your program should stop.");
        }

        File file = new File(databaseFileName);

        if (!file.exists()) {
            return CheckResult.wrong("Can't find a database file. It should be named 'carsharing.mv.db'" +
                    " and located in /carsharing/db/ folder.");
        }

        return correct();
    }

    @DynamicTestingMethod
    public CheckResult test3_checkDatabaseConnection() {
        db.getConnection();
        return correct();
    }

    @DynamicTestingMethod
    public CheckResult test4_checkIfTableExists() {
        if (!db.ifTableExist("COMPANY")) {
            return wrong("Can't find table named 'company'");
        }
        return correct();
    }

    @DynamicTestingMethod
    public CheckResult test5_checkTableColumns() {
        String[][] columns = {{"ID", "INT"}, {"NAME", "VARCHAR"}};
        db.ifColumnsExist("COMPANY", columns);
        db.checkCompanyColumnProperties();
        return correct();
    }

    @DynamicTestingMethod
    public CheckResult test6_testInsert() {

        TestedProgram program = new TestedProgram();
        program.start("-databaseFileName", "carsharing");

        db.clearCompanyTable();

        program.execute("1");
        String output = program.execute("1");

        if (!output.contains("The company list is empty")) {
            return CheckResult.wrong("If no company has been created you should print 'The company list is empty' when '1. Company list' item is chosen");
        }

        output = program.execute("2");

        if (!output.contains("Enter the company name")) {
            return CheckResult.wrong("After choosing '2. Create a company' item you should ask to enter a company name.\n" +
                    "Your output should contain 'Enter the company name:'");
        }

        program.execute("Super company");
        output = program.execute("1");

        if (!output.contains("1. Super company")) {
            return CheckResult.wrong("In the company list expected one company.\n" +
                    "Your output should contain '1. Super company'");
        }

        db.checkCompany("Super company");

        program.execute("2\nAnother company");
        program.execute("2\nOne more company");

        db.checkCompany("Another company");
        db.checkCompany("One more company");

        output = program.execute("1");

        if (!output.contains("1. Super company")) {
            return CheckResult.wrong("In the company list expected one company.\n" +
                    "Your output should contain '1. Super company'.\n" +
                    "Companies should be sorted by 'ID' column");
        }

        if (!output.contains("2. Another company")) {
            return CheckResult.wrong("In the company list expected one company.\n" +
                    "Your output should contain '2. Another company'.\n" +
                    "Companies should be sorted by 'ID' column");
        }

        if (!output.contains("3. One more company")) {
            return CheckResult.wrong("In the company list expected one company.\n" +
                    "Your output should contain '2. Another company'.\n" +
                    "Companies should be sorted by 'ID' column");
        }

        program.execute("0");
        program.execute("0");

        return CheckResult.correct();
    }

    private CheckResult wrong(String message) {
        db.closeConnection();
        return CheckResult.wrong(message);
    }

    private CheckResult correct() {
        db.closeConnection();
        return CheckResult.correct();
    }
}
