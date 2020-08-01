import org.fix.parser.Fixer;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FixerTests {

    @Test
    public void verifyThatFixerIsAbleToParseRawFixMessagesIntoHumanReadableFormat() {
        String msg1 = "35=D;49=Sender;56=Target;11=OrderID_1;109=12345;55=INFY;207=NS;40=2;";
        assertThat(Fixer.translate(msg1),
                is("ClOrdID = OrderID_1\n" +
                        "Symbol = INFY\n" +
                        "TargetCompID = Target\n" +
                        "MsgType = D\n" +
                        "SenderCompID = Sender\n" +
                        "SecurityExchange = NS\n" +
                        "ClientID = 12345\n" +
                        "OrdType = 2\n"));
    }

    @Test
    public void verifyThatFixerReturnsTagIdsIfItDoesNotMatchFixTagMappings() {
        String msg1 = "35=D;49=Sender;56=Target;11=OrderID_1;109=12345;55=INFY;207=NS;40=2;11111=Dummy Tag1;5150=Dummy Tag2;";
        assertThat(Fixer.translate(msg1),
                is("ClOrdID = OrderID_1\n" +
                        "Symbol = INFY\n" +
                        "TargetCompID = Target\n" +
                        "MsgType = D\n" +
                        "SenderCompID = Sender\n" +
                        "11111 = Dummy Tag1\n" +
                        "SecurityExchange = NS\n" +
                        "ClientID = 12345\n" +
                        "OrdType = 2\n" +
                        "5150 = Dummy Tag2\n"));
    }

    @Test
    public void verifyThatCompareFIXreturnsMismatchedTagsAndCorrespondingValues() {
        Map<String, String> m1 = Fixer.convertToAMap("35=D;49=Sender;56=Target;11=OrderID_1;109=12345;55=INFY;207=NS;40=2;11111=Dummy Tag1;5150=Dummy Tag2;");
        Map<String, String> m2 = Fixer.convertToAMap("35=D;49=Sende;56=Target;11=OrderID_1;109=12345;55=INF;207=NS;40=2;11111=Dumm Tag1;5150=Dummy Tag2;");
        assertThat(Fixer.compareFix(m1, m2),
                is("Expected 'Sender' v/s Actual 'Sende' for the FIX Tag SenderCompID\n" +
                        "Expected 'INFY' v/s Actual 'INF' for the FIX Tag Symbol\n" +
                        "Expected 'Dummy Tag1' v/s Actual 'Dumm Tag1' for the FIX Tag 11111\n"));
    }

    @Test
    public void verifyThatCompareFIXAndReturnsNullIfTheMessagesMatch() {
        Map<String, String> m1 = Fixer.convertToAMap("8=FIX.4.2;9=176;35=8;49=PHLX;56=PERS;52=20071123-05:30:00.000;11=ATOMNOCCC9990900;20=3;150=E;39=E;55=MSFT;167=CS;54=1;38=15;40=2;44=15;58=PHLX EQUITY TESTING;59=0;47=C;32=0;31=0;151=15;14=0;6=0;10=128;");
        Map<String, String> m2 = Fixer.convertToAMap("8=FIX.4.2;9=176;35=8;49=PHLX;56=PERS;52=20071123-05:30:00.000;11=ATOMNOCCC9990900;20=3;150=E;39=E;55=MSFT;167=CS;54=1;38=15;40=2;44=15;58=PHLX EQUITY TESTING;59=0;47=C;32=0;31=0;151=15;14=0;6=0;10=128;");
        assertThat(Fixer.compareFix(m1, m2),
                is(""));
    }

    @Test
    public void verifyThatCompareFIXComplainsIfTheTagsInTwoMsgsDontMatch() {
        Map<String, String> m1 = Fixer.convertToAMap("8=FIX.4.2;9=176;35=8;49=PHLX;56=PERS;52=20071123-05:30:00.000;11=ATOMNOCCC9990900;20=3;150=E;39=E;55=MSFT;167=CS;54=1;38=15;40=2;44=15;58=PHLX EQUITY TESTING;59=0;47=C;32=0;31=0;151=15;14=0;6=0;10=128;");
        Map<String, String> m2 = Fixer.convertToAMap("8=FIX.4.2;9=176;35=8;49=PHLX;56=PERS;11=ATOMNOCCC9990900;20=3;150=E;39=E;167=CS;54=1;38=15;40=2;58=PHLX EQUITY TESTING;59=0;47=C;32=0;31=0;151=15;14=0;6=0;10=128;");
        assertThat(Fixer.compareFix(m1, m2),
                is("Expected \n" +
                        "[SenderCompID, BeginString, SecurityType, BdyLength, OrderQty, Symbol, TargetCompID, LastQty, Side, AvgPx, Rule80A(No Longer Used), SendingTime, OrdStatus, TimeInForce, ExecType, LeavesQty, LastPx, CumQty, OrdType, ClOrdID, Text, MsgType, EecTransType, Price, CeckSum]\n" +
                        " v/s Actual\n" +
                        "[SenderCompID, BeginString, SecurityType, BdyLength, CumQty, LastPx, OrderQty, OrdType, TargetCompID, ClOrdID, Text, LastQty, MsgType, Side, AvgPx, Rule80A(No Longer Used), EecTransType, OrdStatus, TimeInForce, ExecType, LeavesQty, CeckSum]"));
    }
}