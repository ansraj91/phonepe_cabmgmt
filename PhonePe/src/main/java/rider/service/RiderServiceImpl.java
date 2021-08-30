package rider.service;

import exceptions.RiderException;
import rider.models.Rider;
import storage.I_StorageService;
import storage.StorageServiceImpl;

public class RiderServiceImpl implements I_RiderService{
    I_StorageService storageService;
    public RiderServiceImpl(I_StorageService storageService){
        this.storageService = storageService;
    }
    @Override
    public Boolean register(Rider rider) throws RiderException {
        this.storageService.saveRider(rider);
        return true;
    }
}
