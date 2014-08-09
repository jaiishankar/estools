Ext.define('estools.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    padding: 5,
    requires: ['estools.store.User'],
    initComponent: function() {
        this.items = [{
                xtype: 'applicationheader',
                region: 'north',
                bodyStyle: 'background:transparent;'
            },
            {
                region: 'center',
                xtype: 'applicationmainscreen',
                border: 0,
                activeTab: 0

            }],
        // Call parent's initComponent method
        this.callParent();
    }
});    