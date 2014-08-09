Ext.define('estools.view.sizing.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.sizingedit',
    // Default proeprties
    layout: 'fit',
    autoShow: false,
    
    /**
     * Initialize this component.
     */
    initComponent: function() {
        // Set the window's title
        this.title = 'Add/Edit Sizing Window';

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
                        itemId: 'sizingNameField',
                        name: 'sizeName',
                        fieldLabel: 'Sizing Name'
                    },
                    {
                        xtype: 'numberfield',
                        itemId: 'sizingValueField',
                        name: 'sizeValue',
                        fieldLabel: 'Sizing Value'
                    },
                    {
                        xtype: 'textfield',
                        itemId: 'uomValueField',
                        name: 'uom',
                        fieldLabel: 'Unit of Measurement',
                        value:'MM'
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