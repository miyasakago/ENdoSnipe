/*
 * Copyright (c) 2004-2013 Acroquest Technology Co., Ltd. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * THE  SOFTWARE IS  PROVIDED BY  Acroquest Technology Co., Ltd., WITHOUT  WARRANTY  OF
 * ANY KIND,  EXPRESS  OR IMPLIED,  INCLUDING BUT  NOT LIMITED  TO THE
 * WARRANTIES OF  MERCHANTABILITY,  FITNESS FOR A  PARTICULAR  PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDER BE LIABLE FOR ANY
 * CLAIM, DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package jp.co.acroquest.endosnipe.web.explorer.listener.collector;

import java.util.ArrayList;
import java.util.List;

import jp.co.acroquest.endosnipe.communicator.AbstractTelegramListener;
import jp.co.acroquest.endosnipe.communicator.entity.Body;
import jp.co.acroquest.endosnipe.communicator.entity.Telegram;
import jp.co.acroquest.endosnipe.communicator.entity.TelegramConstants;
import jp.co.acroquest.endosnipe.web.explorer.dto.TreeMenuDto;
import jp.co.acroquest.endosnipe.web.explorer.manager.EventManager;
import jp.co.acroquest.endosnipe.web.explorer.manager.ResourceSender;
import jp.co.acroquest.endosnipe.web.explorer.util.TreeMenuUtil;

import org.wgp.manager.WgpDataManager;

/**
 * listener class for tree node adding
 * 
 * @author heinminn
 *
 */
public class TreeStateAddListener extends AbstractTelegramListener
{

    /**
     * constructor of this class
     */
    public TreeStateAddListener()
    {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Telegram doReceiveTelegram(final Telegram telegram)
    {
        Body[] resourceAlarmBodys = telegram.getObjBody();

        for (Body body : resourceAlarmBodys)
        {
            List<TreeMenuDto> treeMenuDtoList = new ArrayList<TreeMenuDto>();
            String itemNameInTelegram = body.getStrItemName();
            if (TelegramConstants.ITEMNAME_TREE_ADD.equals(itemNameInTelegram))
            {
                Object[] measurementItemValues = body.getObjItemValueArr();
                for (Object itemValues : measurementItemValues)
                {
                    String measurementItemName = (String)itemValues;
                    int tempIndex = measurementItemName.lastIndexOf("/");

                    String treeId = measurementItemName;
                    String parentTreeId = measurementItemName.substring(0, tempIndex);
                    String data =
                            measurementItemName.substring(tempIndex + 1,
                                                          measurementItemName.length());
                    String type = "target";
                    String icon = "leaf";

                    TreeMenuDto treeMenuDto = new TreeMenuDto();

                    treeMenuDto.setId(TreeMenuUtil.getCannonicalId(treeId));
                    treeMenuDto.setData(data);
                    treeMenuDto.setIcon(icon);
                    treeMenuDto.setParentTreeId(parentTreeId);
                    treeMenuDto.setTreeId(treeId);
                    treeMenuDto.setType(type);

                    treeMenuDtoList.add(treeMenuDto);
                }

                EventManager eventManager = EventManager.getInstance();
                WgpDataManager dataManager = eventManager.getWgpDataManager();
                ResourceSender resourceSender = eventManager.getResourceSender();
                if (dataManager == null || resourceSender == null)
                {
                    return null;
                }

                String type = "add";

                resourceSender.send(treeMenuDtoList, type);

            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getByteRequestKind()
    {
        return TelegramConstants.BYTE_REQUEST_KIND_NOTIFY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getByteTelegramKind()
    {
        return TelegramConstants.BYTE_TELEGRAM_KIND_TREE_ADD_DEFINITION;
    }
}
