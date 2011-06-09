/* *
 * Copyright (c) 2010, Sebastian Sdorra
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of SCM-Manager; nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * http://bitbucket.org/sdorra/scm-manager
 * 
 */

Sonia.repository.Grid = Ext.extend(Sonia.rest.Grid, {

  colNameText: 'Name',
  colTypeText: 'Type',
  colContactText: 'Contact',
  colDescriptionText: 'Description',
  colCreationDateText: 'Creation date',
  colUrlText: 'Url',
  emptyText: 'No repository is configured',
  formTitleText: 'Repository Form',

  initComponent: function(){

    var repositoryStore = new Sonia.rest.JsonStore({
      id: 'repositoryStore',
      proxy: new Ext.data.HttpProxy({
        url: restUrl + 'repositories.json',
        disableCaching: false
      }),
      fields: [ 'id', 'name', 'type', 'contact', 'description', 'creationDate', 'url', 'public', 'permissions' ],
      sortInfo: {
        field: 'name'
      }
    });

    var repositoryColModel = new Ext.grid.ColumnModel({
      defaults: {
        sortable: true,
        scope: this,
        width: 125
      },
      columns: [
        {id: 'name', header: this.colNameText, dataIndex: 'name'},
        {id: 'type', header: this.colTypeText, dataIndex: 'type', renderer: this.renderRepositoryType, width: 80},
        {id: 'contact', header: this.colContactText, dataIndex: 'contact', renderer: this.renderMailto},
        {id: 'description', header: this.colDescriptionText, dataIndex: 'description'},
        {id: 'creationDate', header: this.colCreationDateText, dataIndex: 'creationDate', renderer: Ext.util.Format.formatTimestamp},
        {id: 'Url', header: this.colUrlText, dataIndex: 'url', renderer: this.renderUrl, width: 250}
      ]
    });

    var config = {
      autoExpandColumn: 'description',
      store: repositoryStore,
      colModel: repositoryColModel,
      emptyText: this.emptyText,
      listeners: {
        fallBelowMinHeight: {
          fn: this.onFallBelowMinHeight,
          scope: this
        }
      }
    };

    Ext.apply(this, Ext.apply(this.initialConfig, config));
    Sonia.repository.Grid.superclass.initComponent.apply(this, arguments);
  },

  onFallBelowMinHeight: function(height, minHeight){
    var p = Ext.getCmp('repositoryEditPanel');
    this.setHeight(minHeight);
    var epHeight = p.getHeight();
    p.setHeight(epHeight - (minHeight - height));
    // rerender
    this.doLayout();
    p.doLayout();
    this.ownerCt.doLayout();
  },

  selectItem: function(item){
    if ( debug ){
      console.debug( item.name + ' selected' );
    }

    var infoPanel = main.getInfoPanel(item.type);
    infoPanel.item = item;
    
    var panels = [infoPanel];
    
    if ( Sonia.repository.isOwner(item) ){
      Ext.getCmp('repoRmButton').setDisabled(false);
      panels.push({
        item: item,
        xtype: 'repositoryPropertiesForm',
        onUpdate: {
          fn: this.reload,
          scope: this
        },
        onCreate: {
          fn: this.reload,
          scope: this
        }
      },{
        item: item,
        xtype: 'repositoryPermissionsForm',
        listeners: {
          updated: {
            fn: this.reload,
            scope: this
          },
          created: {
            fn: this.reload,
            scope: this
          }
        }
      });
    } else {
      Ext.getCmp('repoRmButton').setDisabled(true);
    }

    Sonia.repository.setEditPanel(panels);
  },

  renderRepositoryType: function(repositoryType){
    return repositoryTypeStore.queryBy(function(rec){
      return rec.data.name == repositoryType;
    }).itemAt(0).data.displayName;
  }
  
});

// register xtype
Ext.reg('repositoryGrid', Sonia.repository.Grid);