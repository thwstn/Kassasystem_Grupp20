import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.UUID;


public class OrderTest {

    private Order order;
    private OrderLine orderLine1;
    private OrderLine orderLine2;
    private OrderLine orderLine3;
    private OrderLine orderLine4;
    private OrderLine orderLine5;
    private OrderLine orderLine6;
    @BeforeEach
    void init(){
        order = new Order(new Employee("Håkan", 20000), new Customer("Johan", 23));
        orderLine1 = Mockito.mock(OrderLine.class);
        orderLine2 = Mockito.mock(OrderLine.class);
        orderLine3 = Mockito.mock(OrderLine.class);
        orderLine4 = Mockito.mock(OrderLine.class);
        orderLine5 = Mockito.mock(OrderLine.class);
        orderLine6 = Mockito.mock(OrderLine.class);
        Mockito.when(orderLine1.getName()).thenReturn("Gurka");
        Mockito.when(orderLine2.getName()).thenReturn("Tomat");
        Mockito.when(orderLine3.getName()).thenReturn("Morot");
        Mockito.when(orderLine4.getName()).thenReturn("Fikon");
        Mockito.when(orderLine5.getName()).thenReturn("Gurka");
        Mockito.when(orderLine6.getName()).thenReturn("Gurka");
        Mockito.when(orderLine1.getPrice()).thenReturn(5.0);
        Mockito.when(orderLine2.getPrice()).thenReturn(8.0);
        Mockito.when(orderLine3.getPrice()).thenReturn(9.0);
        Mockito.when(orderLine4.getPrice()).thenReturn(50.0);
        Mockito.when(orderLine5.getPrice()).thenReturn(5.0);
        Mockito.when(orderLine6.getPrice()).thenReturn(5.0);
        Mockito.when(orderLine1.getQuantity()).thenReturn(2);
        Mockito.when(orderLine2.getQuantity()).thenReturn(4);
        Mockito.when(orderLine3.getQuantity()).thenReturn(6);
        Mockito.when(orderLine4.getQuantity()).thenReturn(1);
        Mockito.when(orderLine5.getQuantity()).thenReturn(4);
        Mockito.when(orderLine6.getQuantity()).thenReturn(3);

    }

    @Test
    void AddOrderLineToListAddsCorrectly(){
        order.addOrderLineToList(orderLine1);
        Assertions.assertEquals("Gurka",order.getOrderLine("Gurka").getName());




    }

    @Test
    void AddingNullObjectToListThrowsException(){
        orderLine1 = null;
        Assertions.assertThrows(IllegalArgumentException.class, ()  -> order.addOrderLineToList(orderLine1));
    }


    @Test
    void AddingOrderLineWithNegativePriceThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.addOrderLineToList(new OrderLine("Butter", -2.0, 10 )));

    }

    @Test
    void AddingOrderLineWithEmptyNameThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.addOrderLineToList(new OrderLine("", 10, 20)));
    }

    @Test
    void AddingOrderLineWithOnlySpacesThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.addOrderLineToList(new OrderLine("    ", 10,20)));
    }

    @Test
    void AddingOrderLineWithZeroQuantityThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.addOrderLineToList(new OrderLine("Gurka", 10, 0)));
    }

    @Test
    void AddingOrderLineWithNegativeQuantityThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> order.addOrderLineToList(new OrderLine("Tomat", 10.0, -1)));
    }
    @Test
    void AddingOrderLineDuplicateDoesntAddDuplicates(){
        order.addOrderLineToList(orderLine1);
        String s1 = order.toString();
        order.addOrderLineToList(orderLine1);
        String s2 = order.toString();

        Assertions.assertEquals(s1, s2);

    }

    @Test
    void CreatingNewOrderWithoutCustomerSetsCustomerToDefault(){
        Order order1 = new Order(new Employee("Johan", 25000), orderLine1, orderLine2);
        Customer defaultCustomer = order1.getCustomer();
        Assertions.assertEquals(new Customer("Kund", 0), defaultCustomer);
    }

    @Test
    void ModifiyngUnmodifiableListThrowsException(){
        Order order2 = new Order(new Employee("John", 20000), new Customer("Maria", 25), orderLine1, orderLine2);
        Collection<OrderLine> orderLineList = order2.getOrderLineList();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> orderLineList.remove(orderLine1));

    }
    @Test
    void RemoveOrderLineFromListRemovesObject(){
        order.addOrderLineToList(orderLine1);
        order.removeOrderLineFromList(orderLine1);
        Assertions.assertNull(order.getOrderLine(orderLine1.getName()));
    }

    @Test
    void RemovingOrderLineFromListThatDoesntExistDoesNothing(){
        order.addOrderLineToList(orderLine1);
        order.removeOrderLineFromList(orderLine2);

    }

    @Test
    void SortingOrderLinesByAlphabeticalOrderWorks(){
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine2);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine4);
        order.sortByAlphabeticalOrderAscending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.getName()).append(": ").append(ol.getPrice()).append("\n");
        }
        Assertions.assertEquals("Fikon: 50.0\nGurka: 5.0\nMorot: 9.0\nTomat: 8.0\n", answer.toString());


    }

    @Test
    void SortingOrderLinesByAlphabeticalOrderDescendingWorks(){
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine2);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine4);
        order.sortByAlphabeticalOrderDescending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.getName()).append(": ").append(ol.getPrice()).append("\n");
        }
        Assertions.assertEquals("Tomat: 8.0\nMorot: 9.0\nGurka: 5.0\nFikon: 50.0\n", answer.toString());

    }

    @Test
    void SortingOrdersByQuantityHighestToLowestWorks(){
        order.addOrderLineToList(orderLine5);
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine6);

        order.sortByQuantityHighestToLowest();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.getName()).append(": ").append(ol.getPrice()).append(" x").append(ol.getQuantity()).append("\n");
        }
        Assertions.assertEquals("Morot: 9.0 x6\nGurka: 5.0 x4\nGurka: 5.0 x3\nGurka: 5.0 x2\n", answer.toString());
    }

    @Test
    void SortingEmptyListDoesntThrowException(){
        Assertions.assertDoesNotThrow(order :: sortByAlphabeticalOrderAscending);
        Assertions.assertDoesNotThrow(order :: sortByAlphabeticalOrderDescending);
    }

    @Test
    void groupAllOrderLinesTogetherWorks(){ //Testar utan Mocks då OrderLines nu är Implementerad och klar

        order.addOrderLineToList(new OrderLine("Tomat", 8.0, 4 ));
        order.addOrderLineToList(new OrderLine("Gurka", 5.0, 2 ));
        order.addOrderLineToList(new OrderLine("Morot", 9.0, 6 ));
        order.addOrderLineToList(new OrderLine("Gurka", 5.0, 5 ));
        order.addOrderLineToList(new OrderLine("Fikon", 50.0, 1 ));
        order.addOrderLineToList(new OrderLine("Gurka", 5.0, 2 ));

        order.groupAllOrderLinesTogether();
        order.sortByAlphabeticalOrderAscending();
        Assertions.assertEquals("Fikon: 50.0 x1 50.0:-\nGurka: 5.0 x9 45.0:-\nMorot: 9.0 x6 54.0:-\nTomat: 8.0 x4 32.0:-\n", order.toString());
    }

    @Test
    void GroupingEmptyOrderThrowsNoException(){
        Assertions.assertDoesNotThrow(order :: groupAllOrderLinesTogether);

    }

    @Test
    void totalAmountComputesCorrectly(){
        fillOrderWIthRealData();
        Assertions.assertEquals(196.0, order.getTotalAmount());
    }

    @Test
    void RemovingOrderLineUpdatesTotalAmount(){
        fillOrderWIthRealData();
        OrderLine o = new OrderLine("Gurka", 5.0, 10);
        order.addOrderLineToList(o);
        order.removeOrderLineFromList(o);
        Assertions.assertEquals(196,order.getTotalAmount());
    }

    @Test
    void GetReceiptFormatsCorrectly(){
        fillOrderWIthRealData();
        Assertions.assertEquals("""
                \t\t\tBillys
                \tHandla smart, Bunkra hårt
                Gurka: 5.0 x2 10.0:-
                Tomat: 8.0 x4 32.0:-
                Morot: 9.0 x6 54.0:-
                Pasta: 10.0 x10 100.0:-

                Totalt:\t196.0:-
                Du betjänades av: Håkan
                Tack för att du handlade hos oss Johan!
                """ + order.getDate().toString(), order.getReceipt());
    }

    @Test
    void AddingOrderLineToOrderThatIsPaidThrowsException(){
        order.debitOrder();
        Assertions.assertThrows(IllegalStateException.class, () -> order.addOrderLineToList(orderLine1));
    }

    @Test
    void RemovingOrderLineFromOrderThatIsPaidThrowsException(){
        order.addOrderLineToList(orderLine1);
        order.debitOrder();
        Assertions.assertThrows(IllegalStateException.class, () -> order.removeOrderLineFromList(orderLine1));
    }

    @Test
    void FinalizingOrderWorks(){
        fillOrderWIthRealData();
        order.debitOrder();
        Assertions.assertTrue(order.isOrderPaid());

    }


    private void fillOrderWIthRealData(){ //Metod skriven efter jag prövat på mockning. OrderLine är nu implementerad
        order.addOrderLineToList(new OrderLine("Gurka", 5.0, 2));
        order.addOrderLineToList(new OrderLine("Tomat", 8.0, 4));
        order.addOrderLineToList(new OrderLine("Morot", 9.0, 6));
        order.addOrderLineToList(new OrderLine("Pasta", 10.0, 10));
    }

}
