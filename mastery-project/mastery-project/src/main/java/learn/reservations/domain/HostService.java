package learn.reservations.domain;

import learn.reservations.data.DataException;
import learn.reservations.data.HostRepository;
import learn.reservations.models.Host;

import java.util.List;
import java.util.stream.Collectors;

public class HostService {
    private final HostRepository hostRepository;
    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }
    public Host findByHostEmail(String hostEmail) {
        return hostRepository.findAll().stream()
                .filter(h -> h.getEmail().equalsIgnoreCase(hostEmail))
                .findFirst()
                .orElse(null);
    }
}
