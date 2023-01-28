package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.DetailsOrder;
import ru.lernup.bookstore.model.DetailsOrderDto;
import ru.lernup.bookstore.view.DetailsOrderView;

@Component
public class DetailsOrderMapper {
    public DetailsOrderDto mappedToDto(DetailsOrder detailsOrder){
        return
        DetailsOrderDto.builder()
                .idBook(detailsOrder.getIdBook().getId())
                .quantity(detailsOrder.getQuantity())
                .id(detailsOrder.getId())
                .build();
    }
    public DetailsOrderView mappedToView(DetailsOrderDto detailsOrderDto){
        DetailsOrderView detailsOrderView =new DetailsOrderView();
        detailsOrderView.setQuantity(detailsOrderDto.getQuantity());
        detailsOrderView.setIdBook(detailsOrderDto.getIdBook());
        detailsOrderView.setId(detailsOrderDto.getId());
        return detailsOrderView;
    }
    public DetailsOrderDto mappedFromView(DetailsOrderView detailsOrderView){
        return
                DetailsOrderDto.builder().
                        quantity(detailsOrderView.getQuantity())
                        .idBook(detailsOrderView.getIdBook())
                        .id(detailsOrderView.getId())
                        .build();
    }
    public DetailsOrder mappedFromDto(DetailsOrderDto detailsOrderDto){
        DetailsOrder detailsOrder = new DetailsOrder();
        detailsOrder.setId(detailsOrderDto.getId());
        detailsOrder.setQuantity(detailsOrderDto.getQuantity());
        return detailsOrder;
    }
}
