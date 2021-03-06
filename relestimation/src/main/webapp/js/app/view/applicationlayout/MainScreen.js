Ext.define('estools.view.applicationlayout.MainScreen', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.applicationmainscreen',
    border: false,
    /**
     * Initialize Component.
     */
    initComponent: function() {
        this.bbar = [{
                xtype: 'label',
                text: 'Developed and Owned by - Jaishankararam Venkatesan contact: jaiishankar@gmail.com'
            }];
        this.items = [
            {
                xtype: 'projectmasterpanel',
                iconCls: 'icon-project',
                itemId: 'projectmastertab'
            }, {
                xtype: 'usersmasterpanel',
                iconCls: 'icon-user',
                itemId: 'usermastertab'
            }, {
                xtype: 'devgroupmasterpanel',
                iconCls: 'icon-group',
                itemId: 'groupsmastertab'
            }, {
                xtype: 'sizingmasterpanel',
                iconCls: 'icon-sizing',
                itemId: 'sizingmastertab'

            }, {
                xtype: 'prioritymasterpanel',
                iconCls: 'icon-priority',
                itemId: 'prioritymastertab'
            }, {
                xtype: 'assumtypemasterpanel',
                itemId: 'assumptionmastertab'
            }, {
                xtype: 'usersettings',
                iconCls: 'icon-settings'
            }];
        this.callParent();
        this.on('render', this.setUserActions, this);
    },
    setUserActions: function() {
        this.down('#usermastertab').setDisabled(!globalvar.isAdminUser);
        this.down('#groupsmastertab').setDisabled(!globalvar.isAdminUser);
        this.down('#sizingmastertab').setDisabled(!globalvar.isAdminUser);
        this.down('#prioritymastertab').setDisabled(!globalvar.isAdminUser);
        this.down('#assumptionmastertab').setDisabled(!globalvar.isAdminUser);
    }
});