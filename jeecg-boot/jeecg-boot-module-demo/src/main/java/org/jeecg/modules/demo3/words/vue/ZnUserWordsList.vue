<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('用户汉字完成表')">导出</a-button>
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

    <zn-user-words-modal ref="modalForm" @ok="modalFormOk"></zn-user-words-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnUserWordsModal from './modules/ZnUserWordsModal'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'

  export default {
    name: 'ZnUserWordsList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ZnUserWordsModal,
      JSuperQuery,
    },
    data () {
      return {
        description: '用户汉字完成表管理页面',
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
            title:'用户id',
            align:"center",
            dataIndex: 'userId'
          },
          {
            title:'汉字id',
            align:"center",
            dataIndex: 'wordsId'
          },
          {
            title:'分组id',
            align:"center",
            dataIndex: 'groupId'
          },
          {
            title:'是否检查过',
            align:"center",
            dataIndex: 'ifChecked'
          },
          {
            title:'是否通过',
            align:"center",
            dataIndex: 'ifPassed'
          },
          {
            title:'成功的输入',
            align:"center",
            dataIndex: 'input'
          },
          {
            title:'反馈类型',
            align:"center",
            dataIndex: 'feedbackType'
          },
          {
            title:'反馈备注',
            align:"center",
            dataIndex: 'feedbackRemark'
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
          list: "/words/znUserWords/list",
          delete: "/words/znUserWords/delete",
          deleteBatch: "/words/znUserWords/deleteBatch",
          exportXlsUrl: "/words/znUserWords/exportXls",
          importExcelUrl: "words/znUserWords/importExcel",
          
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
        fieldList.push({type:'string',value:'userId',text:'用户id',dictCode:''})
        fieldList.push({type:'int',value:'wordsId',text:'汉字id',dictCode:''})
        fieldList.push({type:'int',value:'groupId',text:'分组id',dictCode:''})
        fieldList.push({type:'int',value:'ifChecked',text:'是否检查过',dictCode:''})
        fieldList.push({type:'int',value:'ifPassed',text:'是否通过',dictCode:''})
        fieldList.push({type:'string',value:'input',text:'成功的输入',dictCode:''})
        fieldList.push({type:'int',value:'feedbackType',text:'反馈类型',dictCode:''})
        fieldList.push({type:'string',value:'feedbackRemark',text:'反馈备注',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>