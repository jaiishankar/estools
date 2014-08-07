Ext.define('estools.model.Sizing', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'sizeName', type: 'string'},
        {name: 'sizeValue', type: 'int'},
        {name: 'uom', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: './v1/sizing',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});