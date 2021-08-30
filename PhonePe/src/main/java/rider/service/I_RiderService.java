package rider.service;

import exceptions.RiderException;
import rider.models.Rider;

public interface I_RiderService {
     Boolean register(Rider rider) throws RiderException;
}
