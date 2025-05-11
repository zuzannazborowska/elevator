package el.ev.business.domain.dto;

import el.ev.business.domain.model.Direction;

public record CallRequest(
        int floor,
        Direction direction
){}
