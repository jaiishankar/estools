Ext.define('estools.controller.User', {
    extend: 'Ext.app.Controller',

    views: [
        'users.MasterPanel',
        'users.Edit'
    ],
    
    stores: [
        'User'
    ],
    
    models: [
        'User'
    ],
    
    init: function() {
        this.control({     
            'usersgrid': {
                itemdblclick: this.onItemDblClick
            },
            
            'useredit button[action=save]': {
                click: this.updateProcess
            }
        });
    },
    
    onItemDblClick: function(grid, record) { 
        
        var view = Ext.widget('useredit');        
        var form = view.down('form');
        
        form.loadRecord(record);
        form.store = grid.store; 
        view.show();
    },
    
    updateProcess: function(button) {
        var win    = button.up('window'),
            form   = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        form.store.sync();
        win.close();          
    }
});