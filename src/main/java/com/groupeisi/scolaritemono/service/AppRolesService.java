package com.groupeisi.scolaritemono.service;

import com.groupeisi.scolaritemono.dao.IAppRolesRepository;
import com.groupeisi.scolaritemono.dto.AppRoles;
import com.groupeisi.scolaritemono.exception.EntityNotFoundException;
import com.groupeisi.scolaritemono.exception.RequestException;
import com.groupeisi.scolaritemono.mapping.AppRolesMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppRolesService {
    private IAppRolesRepository iAppRolesRepository;
    private AppRolesMapper appRolesMapper;
    MessageSource messageSource;

    public AppRolesService(IAppRolesRepository iAppRolesRepository, AppRolesMapper appRolesMapper, MessageSource messageSource) {
        this.iAppRolesRepository = iAppRolesRepository;
        this.appRolesMapper = appRolesMapper;
        this.messageSource = messageSource;
    }


    @Transactional(readOnly = true)
    public List<AppRoles> getAppRoles(){


        return StreamSupport.stream(iAppRolesRepository.findAll().spliterator(),false)
                .map(appRolesMapper::toAppRoles)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppRoles getAppRole(int id){
        return appRolesMapper.toAppRoles(iAppRolesRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    @Transactional
    public AppRoles createAppRoles(AppRoles appRoles){
        return appRolesMapper.toAppRoles(iAppRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
    }

    @Transactional
    public AppRoles updateAppRoles(int id, AppRoles appRoles){
        return iAppRolesRepository.findById(id)
                .map(entity->{
                    appRoles.setId(id);
                    return appRolesMapper.toAppRoles(iAppRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
                }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteAppRoles(int id){
        try{
            iAppRolesRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("role.errordeletion",new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}

