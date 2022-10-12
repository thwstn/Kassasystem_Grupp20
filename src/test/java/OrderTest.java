import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;




public class OrderTest {

    private Order order;
    private OrderLine orderLine1;
    private OrderLine orderLine2;
    private OrderLine orderLine3;
    private OrderLine orderLine4;
    @BeforeEach
    void init(){

        order = new Order();
        orderLine1 = Mockito.mock(OrderLine.class);
        orderLine2 = Mockito.mock(OrderLine.class);
        orderLine3 = Mockito.mock(OrderLine.class);
        orderLine4 = Mockito.mock(OrderLine.class);
        Mockito.when(orderLine1.getName()).thenReturn("Gurka");
        Mockito.when(orderLine2.getName()).thenReturn("Tomat");
        Mockito.when(orderLine3.getName()).thenReturn("Morot");
        Mockito.when(orderLine4.getName()).thenReturn("Fikon");
        Mockito.when(orderLine1.getPrice()).thenReturn(5.0);
        Mockito.when(orderLine2.getPrice()).thenReturn(8.0);
        Mockito.when(orderLine3.getPrice()).thenReturn(9.0);
        Mockito.when(orderLine4.getPrice()).thenReturn(50.0);

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
    void AddingOrderLineDuplicateDoesntAddDuplicates(){
        order.addOrderLineToList(orderLine1);
        String s1 = order.toString();
        order.addOrderLineToList(orderLine1);
        String s2 = order.toString();

        Assertions.assertEquals(s1, s2);

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
        Mockito.when(orderLine1.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine2.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine3.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine4.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine2);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine4);
        order.sortByAplhabeticalOrderAscending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.getName()).append(": ").append(ol.getPrice()).append("\n");
        }
        Assertions.assertEquals("Fikon: 50.0\nGurka: 5.0\nMorot: 9.0\nTomat: 8.0\n", answer.toString());


    }


}
