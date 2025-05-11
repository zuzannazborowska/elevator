package el.ev.business.api;

import el.ev.business.domain.dto.CallRequest;
import el.ev.business.domain.model.Elevator;
import el.ev.business.domain.service.ElevatorSystemServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elevators")
@RequiredArgsConstructor
public class ElevatorController {

    private final ElevatorSystemServiceAdapter system;

    @GetMapping
    public List<Elevator> getAll() {
        return system.getAllElevators();
    }

    @PostMapping("/call")
    public void callToFloor(@RequestBody CallRequest request) {
        system.callElevator(request);
    }

    @PostMapping("/{elevatorId}/select")
    public void selectFloor(@PathVariable int elevatorId, @RequestParam int floor) {
        system.selectFloor(elevatorId, floor);
    }
}