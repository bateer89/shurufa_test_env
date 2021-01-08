<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="汉字">
              <a-input placeholder="请输入汉字" v-model="queryParam.words"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="全码">
              <a-input placeholder="请输入全码" v-model="queryParam.keyboardSequence"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码1">
                <a-input placeholder="请输入简码1" v-model="queryParam.keyboardSimpleSequence1"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码2">
                <a-input placeholder="请输入简码2" v-model="queryParam.keyboardSimpleSequence2"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码3">
                <a-input placeholder="请输入简码3" v-model="queryParam.keyboardSimpleSequence3"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="全码笔画">
                <a-input placeholder="请输入全码笔画" v-model="queryParam.allStrokes"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码笔画1">
                <a-input placeholder="请输入简码笔画1" v-model="queryParam.simpleStrokes1"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码笔画2">
                <a-input placeholder="请输入简码笔画2" v-model="queryParam.simpleStrokes2"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="简码笔画3">
                <a-input placeholder="请输入简码笔画3" v-model="queryParam.simpleStrokes3"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('汉字表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <zn-words-modal ref="modalForm" @ok="modalFormOk"></zn-words-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnWordsModal from './modules/ZnWordsModal'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'

  export default {
    name: 'ZnWordsList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ZnWordsModal,
      JSuperQuery,
    },
    data () {
      return {
        description: '汉字表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'汉字',
            align:"center",
            sorter: true,
            dataIndex: 'words'
          },
          {
            title:'全码',
            align:"center",
            sorter: true,
            dataIndex: 'keyboardSequence'
          },
          {
            title:'简码1',
            align:"center",
            sorter: true,
            dataIndex: 'keyboardSimpleSequence1'
          },
          {
            title:'简码2',
            align:"center",
            sorter: true,
            dataIndex: 'keyboardSimpleSequence2'
          },
          {
            title:'简码3',
            align:"center",
            sorter: true,
            dataIndex: 'keyboardSimpleSequence3'
          },
          {
            title:'全码笔画',
            align:"center",
            sorter: true,
            dataIndex: 'allStrokes'
          },
          {
            title:'简码笔画1',
            align:"center",
            sorter: true,
            dataIndex: 'simpleStrokes1'
          },
          {
            title:'简码笔画2',
            align:"center",
            sorter: true,
            dataIndex: 'simpleStrokes2'
          },
          {
            title:'简码笔画3',
            align:"center",
            sorter: true,
            dataIndex: 'simpleStrokes3'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/words/znWords/list",
          delete: "/words/znWords/delete",
          deleteBatch: "/words/znWords/deleteBatch",
          exportXlsUrl: "/words/znWords/exportXls",
          importExcelUrl: "words/znWords/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'words',text:'汉字',dictCode:''})
        fieldList.push({type:'string',value:'keyboardSequence',text:'全码',dictCode:''})
        fieldList.push({type:'string',value:'keyboardSimpleSequence1',text:'简码1',dictCode:''})
        fieldList.push({type:'string',value:'keyboardSimpleSequence2',text:'简码2',dictCode:''})
        fieldList.push({type:'string',value:'keyboardSimpleSequence3',text:'简码3',dictCode:''})
        fieldList.push({type:'string',value:'allStrokes',text:'全码笔画',dictCode:''})
        fieldList.push({type:'string',value:'simpleStrokes1',text:'简码笔画1',dictCode:''})
        fieldList.push({type:'string',value:'simpleStrokes2',text:'简码笔画2',dictCode:''})
        fieldList.push({type:'string',value:'simpleStrokes3',text:'简码笔画3',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>