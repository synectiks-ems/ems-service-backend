package com.synectiks.cms.filter.vehicle;

import com.synectiks.cms.business.service.VehicleService;
import com.synectiks.cms.entities.CmsVehicleVo;
import com.synectiks.cms.entities.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleFilterProcessor {
    @Autowired
    private VehicleService vehicleService;

    public List<CmsVehicleVo> searchVehicle(Long transportRouteId, String vehicleNumber) throws Exception {
        return vehicleService.searchVehicle(transportRouteId,vehicleNumber);
    }

    public List<CmsVehicleVo> searchVehicle(VehicleListFilterInput filter) throws Exception {
        return vehicleService.searchVehicle(filter);
    }
}
