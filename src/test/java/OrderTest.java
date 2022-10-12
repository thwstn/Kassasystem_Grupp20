import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class OrderTest {

    private Order order;
    private OrderLine orderLine1;
    private OrderLine orderLine2;
    @BeforeEach
    void init(){

        order = new Order();
        orderLine1 = Mockito.mock(OrderLine.class);
        orderLine2 = Mockito.mock(OrderLine.class);
    }

    @Test
    void AddOrderLineToListAddsCorrectly(){
        Mockito.when(orderLine1.getName()).thenReturn("Gurka");
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
    void PrintingReceiptPrintsCorrectly(){

    }


}
