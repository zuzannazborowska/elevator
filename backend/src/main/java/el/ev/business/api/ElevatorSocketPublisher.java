package el.ev.business.api;

import el.ev.business.domain.ElevatorConstants;
import el.ev.business.domain.model.Elevator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElevatorSocketPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void publishElevatorsState(List<Elevator> elevators) {
        messagingTemplate.convertAndSend(
                ElevatorConstants.ELEVATORS_WEB_SOCKET_DESTINATION,
                elevators
        );
    }
}
