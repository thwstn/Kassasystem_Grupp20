import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        order = new Order(UUID.randomUUID(), new Employee(), new Customer(1, "Johan", 23));
        orderLine1 = Mockito.mock(OrderLine.class);
        orderLine2 = Mockito.mock(OrderLine.class);
        orderLine3 = Mockito.mock(OrderLine.class);
        orderLine4 = Mockito.mock(OrderLine.class);
        orderLine5 = Mockito.mock(OrderLine.class);
        orderLine6 = Mockito.mock(OrderLine.class);
        Mockito.when(orderLine1.name()).thenReturn("Gurka");
        Mockito.when(orderLine2.name()).thenReturn("Tomat");
        Mockito.when(orderLine3.name()).thenReturn("Morot");
        Mockito.when(orderLine4.name()).thenReturn("Fikon");
        Mockito.when(orderLine5.name()).thenReturn("Gurka");
        Mockito.when(orderLine6.name()).thenReturn("Gurka");
        Mockito.when(orderLine1.price()).thenReturn(5.0);
        Mockito.when(orderLine2.price()).thenReturn(8.0);
        Mockito.when(orderLine3.price()).thenReturn(9.0);
        Mockito.when(orderLine4.price()).thenReturn(50.0);
        Mockito.when(orderLine5.price()).thenReturn(5.0);
        Mockito.when(orderLine6.price()).thenReturn(5.0);
        Mockito.when(orderLine1.quantity()).thenReturn(2);
        Mockito.when(orderLine2.quantity()).thenReturn(4);
        Mockito.when(orderLine3.quantity()).thenReturn(6);
        Mockito.when(orderLine4.quantity()).thenReturn(1);
        Mockito.when(orderLine5.quantity()).thenReturn(4);
        Mockito.when(orderLine6.quantity()).thenReturn(3);

    }

    @Test
    void AddOrderLineToListAddsCorrectly(){
        order.addOrderLineToList(orderLine1);
        Assertions.assertEquals("Gurka",order.getOrderLine("Gurka").name());




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
        Assertions.assertNull(order.getOrderLine(orderLine1.name()));
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
        order.sortByAlphabeticalOrderAscending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.name()).append(": ").append(ol.price()).append("\n");
        }
        Assertions.assertEquals("Fikon: 50.0\nGurka: 5.0\nMorot: 9.0\nTomat: 8.0\n", answer.toString());


    }

    @Test
    void SortingOrderLinesByAlphabeticalOrderDescendingWorks(){
        Mockito.when(orderLine1.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine2.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine3.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine4.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine2);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine4);
        order.sortByAlphabeticalOrderDescending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.name()).append(": ").append(ol.price()).append("\n");
        }
        Assertions.assertEquals("Tomat: 8.0\nMorot: 9.0\nGurka: 5.0\nFikon: 50.0\n", answer.toString());

    }

    @Test
    void TwoOrderLinesWithSameNameOrdersByQuantity(){
        Mockito.when(orderLine1.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine3.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine5.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        Mockito.when(orderLine6.compareTo(Mockito.any(OrderLine.class))).thenCallRealMethod();
        order.addOrderLineToList(orderLine5);
        order.addOrderLineToList(orderLine1);
        order.addOrderLineToList(orderLine3);
        order.addOrderLineToList(orderLine6);

        order.sortByAlphabeticalOrderAscending();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++){
            OrderLine ol = order.getOrderLineAtIndex(i);
            answer.append(ol.name()).append(": ").append(ol.price()).append(" x").append(ol.quantity()).append("\n");
        }
        Assertions.assertEquals("Gurka: 5.0 x2\nGurka: 5.0 x3\nGurka: 5.0 x4\nMorot: 9.0 x6\n", answer.toString());
    }

    @Test
    void SortingEmptyListDoesntThrowException(){
        Assertions.assertDoesNotThrow(order :: sortByAlphabeticalOrderAscending);
        Assertions.assertDoesNotThrow(order :: sortByAlphabeticalOrderDescending);
    }



}
