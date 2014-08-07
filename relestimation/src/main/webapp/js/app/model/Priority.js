Ext.define('estools.model.Priority', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'priority', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: './v1/priorities',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});