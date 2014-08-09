Ext.define('estools.view.assumptiontype.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.assumtypeedit',
    // Default proeprties
    layout: 'fit',
    autoShow: false,
    
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit Group Window';

        // Create and add the form
        this.items = [
            {
                xtype: 'form',
                itemId: 'editForm',
                border: 0,
                bodyPadding: 5,
                items: [
                    {
                        xtype: 'hiddenfield',
                        name: 'id',
                        value: 0
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'assumtypenameField',
                        name: 'typeName',
                        fieldLabel: 'Assumption Type Name'
                    }
                ]
            }
        ];

        // Add the save/cancel buttons
        this.buttons = [
            {
                itemId: 'saveButton',
                text: 'Upadte', 
                action: 'save'
            },
            {
                itemId: 'cancelButton',
                text: 'Cancel',
                scope: this,
                handler: this.destroy
            }
        ];

        this.callParent();
    }
});