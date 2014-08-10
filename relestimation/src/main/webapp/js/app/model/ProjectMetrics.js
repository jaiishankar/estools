Ext.define('estools.model.ProjectMetrics', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'projectId', type: 'int'},
        {name: 'description', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url:'',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});