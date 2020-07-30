import org.fix.parser.Fixer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FixerTests {

    @Test
    public void verifyThatFixerIsAbleToParseRawFixMessagesIntoHumanReadableFormat() {
        String msg1 = "35=D;49=Sender;56=Target;11=OrderID_1;109=12345;55=INFY;207=NS;40=2;";
        assertThat(Fixer.translate(msg1),
                is("ClOrdID : OrderID_1\n" +
                        "Symbol : INFY\n" +
                        "TargetCompID : Target\n" +
                        "MgType : D\n" +
                        "SenderCompID : Sender\n" +
                        "SecurityExchange : NS\n" +
                        "ClientID : 12345\n" +
                        "OrdType : 2\n"));
    }

    @Test
    public void verifyThatFixerReturnsTagIdsIfItDoesNotMatchFixTagMappings() {
        String msg1 = "35=D;49=Sender;56=Target;11=OrderID_1;109=12345;55=INFY;207=NS;40=2;11111=Dummy Tag1;5150=Dummy Tag2;";
        assertThat(Fixer.translate(msg1),
                is("ClOrdID : OrderID_1\n" +
                        "Symbol : INFY\n" +
                        "TargetCompID : Target\n" +
                        "MgType : D\n" +
                        "SenderCompID : Sender\n" +
                        "11111 : Dummy Tag1\n" +
                        "SecurityExchange : NS\n" +
                        "ClientID : 12345\n" +
                        "OrdType : 2\n" +
                        "5150 : Dummy Tag2\n"));
    }
}
