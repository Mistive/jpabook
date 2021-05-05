package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class Order extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

//    @Column(name = "MEMBER_ID")
//    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //양방향 편의 메소드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);  //현재 내가 가지고 있는 ItemList에 orderItem을 추가하고,
        orderItem.setOrder(this);   //주문아이템의 주문 내역에 나를 추가한다.
    }
}
