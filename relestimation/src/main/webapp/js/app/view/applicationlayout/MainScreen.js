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
            xtype: 'panel',
            title:'Settings'
        }]
});