Ext.define('estools.view.applicationlayout.MainScreen', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.applicationmainscreen',
    border: false,
    /**
     * Initialize Component.
     */
    initComponent: function() {

        this.callParent();
    },
    items: [
        {
            xtype:'projectmasterpanel',
            iconCls:'icon-project'
        },{
            xtype: 'usersmasterpanel',
            iconCls:'icon-user'
        },{
            xtype:'devgroupmasterpanel',
            iconCls:'icon-group'
        },{
            xtype:'sizingmasterpanel',
            iconCls:'icon-sizing'
            
        },{
            xtype:'prioritymasterpanel',
            iconCls:'icon-priority'
        },{
            xtype: 'panel',
            title:'Settings',
            iconCls:'icon-settings'
        }]
});