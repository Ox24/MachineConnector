package subs;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.utils.AttributesUtil;

/**
 * Created by Timur on 29.11.2015.
 * Copyright Timur Tasci
 */
public class SubsManager implements ISubsManager{

    private static SubsManager manager;

    private SubsManager(){}

    public SubsManager getInstance(){
        if(manager == null)
            manager = new SubsManager();
        return manager;
    }


    public void subscribe(NodeId nodeId) {

    }

}
