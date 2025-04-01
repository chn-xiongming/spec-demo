# Java Spring Web项目开发规范

## 目录

1. [项目架构](#1-项目架构)
2. [目录结构](#2-目录结构)
3. [分层职责](#3-分层职责)
4. [命名规范](#4-命名规范)
5. [开发模式](#5-开发模式)
6. [代码示例](#6-代码示例)

## 1. 项目架构

### 1.1 整体架构

项目采用分层架构，自顶向下分为：
- Web层：处理HTTP请求，实现REST接口
- 服务层：实现核心业务逻辑
- 管理层：提供基础服务能力
- 数据访问层：处理数据持久化

### 1.2 分层设计原则

1. **Web层**：
   - 按业务模块划分：用户、项目、任务、资源、仪表盘等
   - 每个业务模块内按CQRS模式拆分为查询(Query)和命令(Command)
   - 负责请求参数校验、响应封装
   
2. **服务层**：
   - 按功能域划分：
     - 业务功能服务：核心业务逻辑
     - 基础服务：如认证、配置等
     - 外部服务：第三方集成
   - 每个服务拆分为Service和Manager：
     - Service：业务编排和组合
     - Manager：原子业务操作
   
3. **数据访问层**：
   - 采用MyBatis作为ORM框架
   - 支持灵活的SQL编写

### 1.3 模型设计

采用独立的模型设计：
- VO (View Object)：面向展示的视图对象
- BO (Business Object)：业务逻辑对象
- DTO (Data Transfer Object)：服务间传输对象
- PO (Persist Object)：持久化对象

## 2. 目录结构

```
com.example.project
├── web/                              # Web层
│   ├── user/                         # 用户管理模块
│   │   ├── query/                   
│   │   │   ├── UserQueryController
│   │   │   └── UserProfileQueryController
│   │   └── command/
│   │       ├── UserCommandController
│   │       └── UserProfileCommandController
│   ├── project/                      # 项目管理模块
│   │   ├── query/
│   │   │   ├── ProjectQueryController
│   │   │   └── ProjectStatisticsQueryController
│   │   └── command/
│   │       ├── ProjectCommandController
│   │       └── ProjectMemberCommandController
│   ├── task/                         # 任务管理模块
│   │   ├── query/
│   │   │   ├── TaskQueryController
│   │   │   └── TaskTrackingQueryController
│   │   └── command/
│   │       ├── TaskCommandController
│   │       └── TaskAssignmentCommandController
│   ├── resource/                     # 资源管理模块
│   │   ├── query/
│   │   │   ├── ResourceQueryController
│   │   │   └── ResourceUsageQueryController
│   │   └── command/
│   │       ├── ResourceCommandController
│   │       └── ResourceAllocationCommandController
│   └── dashboard/                    # 仪表盘模块
│       ├── query/
│       │   ├── DashboardQueryController
│       │   └── DashboardMetricsQueryController
│       └── command/
│           └── DashboardPreferenceCommandController
├── service/                          # 服务层
│   ├── business/                     # 业务功能服务
│   │   ├── user/
│   │   │   ├── UserService
│   │   │   └── UserServiceImpl
│   │   ├── project/
│   │   ├── task/
│   │   ├── resource/
│   │   └── dashboard/
│   ├── basic/                        # 基础服务
│   │   ├── auth/
│   │   └── config/
│   └── external/                     # 外部服务
│       ├── storage/
│       └── message/
├── manager/                          # 管理层
│   ├── business/
│   │   ├── user/
│   │   │   ├── UserManager
│   │   │   └── UserManagerImpl
│   │   ├── project/
│   │   ├── task/
│   │   ├── resource/
│   │   └── dashboard/
│   ├── basic/
│   └── external/
├── repository/                       # 数据访问层
│   ├── mapper/                       # MyBatis映射接口
│   │   ├── UserMapper
│   │   ├── ProjectMapper
│   │   ├── TaskMapper
│   │   └── ResourceMapper
│   └── dao/                         # 数据访问对象
├── model/                           # 模型定义
│   ├── vo/                         # 视图对象
│   │   ├── user/
│   │   ├── project/
│   │   ├── task/
│   │   └── resource/
│   ├── bo/                         # 业务对象
│   ├── dto/                        # 传输对象
│   └── po/                         # 持久化对象
└── common/                         # 公共组件
    ├── util/                       # 工具类
    ├── constant/                   # 常量定义
    ├── enums/                      # 枚举定义
    └── exception/                  # 异常定义
```

## 3. 分层职责

### 3.1 Web层
- 职责：
  - 接收HTTP请求，进行参数校验
  - 调用Service层处理业务逻辑
  - 封装响应结果
- 特点：
  - 按业务模块划分目录
  - 查询和命令分离
  - 轻量级，不包含业务逻辑

### 3.2 服务层
- 职责：
  - Service：业务流程编排，事务控制
  - Manager：提供原子业务操作
- 特点：
  - 按功能域划分
  - 服务接口与实现分离
  - 遵循单一职责原则

### 3.3 数据访问层
- 职责：
  - 数据持久化操作
  - SQL执行和结果映射
- 特点：
  - 使用MyBatis框架
  - 支持灵活的SQL编写
  - 与业务逻辑解耦

## 4. 命名规范

### 4.1 Controller命名
- 查询控制器：`*QueryController`
- 命令控制器：`*CommandController`

### 4.2 Service命名
- 接口：`*Service`
- 实现类：`*ServiceImpl`

### 4.3 Manager命名
- 接口：`*Manager`
- 实现类：`*ManagerImpl`

### 4.4 数据访问层命名
- MyBatis接口：`*Mapper`
- DAO类：`*DAO`

### 4.5 模型命名
- 视图对象：`*VO`
- 业务对象：`*BO`
- 传输对象：`*DTO`
- 持久化对象：`*PO`

## 5. 开发模式

### 5.1 TDD开发流程
1. 编写接口文档（使用Swagger注解）
2. 编写测试用例
3. 实现代码
4. 运行测试
5. 重构优化

### 5.2 测试规范
- 单元测试覆盖率：80%以上
- 测试类命名：`*Test`
- 测试方法命名：`test[方法名_测试场景]`

## 6. 代码示例

### 6.1 Controller示例

```java
@Api(tags = "项目管理")
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectQueryController {
    @Autowired
    private ProjectService projectService;
    
    @ApiOperation("获取项目详情")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectVO> getProject(@PathVariable Long id) {
        ProjectBO project = projectService.getProject(id);
        return ResponseEntity.ok(project.toVO());
    }
}

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectCommandController {
    @Autowired
    private ProjectService projectService;
    
    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody @Valid ProjectCreateCommand command) {
        projectService.createProject(command.toBO());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
```

### 6.2 Service示例

```java
public interface ProjectService {
    ProjectBO getProject(Long id);
    void createProject(ProjectCreateBO project);
}

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectManager projectManager;
    
    @Override
    public ProjectBO getProject(Long id) {
        return projectManager.getProjectById(id);
    }
    
    @Override
    public void createProject(ProjectCreateBO project) {
        projectManager.createProject(project);
    }
}
```

### 6.3 Manager示例

```java
public interface ProjectManager {
    ProjectBO getProjectById(Long id);
    void createProject(ProjectCreateBO project);
}

@Component
public class ProjectManagerImpl implements ProjectManager {
    @Autowired
    private ProjectMapper projectMapper;
    
    @Override
    public ProjectBO getProjectById(Long id) {
        ProjectPO po = projectMapper.selectById(id);
        return po != null ? po.toBO() : null;
    }
    
    @Override
    public void createProject(ProjectCreateBO project) {
        projectMapper.insert(project.toPO());
    }
}
```

### 6.4 Mapper示例

```java
@Mapper
public interface ProjectMapper {
    @Select("SELECT * FROM project WHERE id = #{id}")
    ProjectPO selectById(@Param("id") Long id);
    
    void insert(ProjectPO project);
}
```

### 6.5 模型转换示例

```java
public class ProjectBO {
    private Long id;
    private String name;
    private String description;
    
    public ProjectVO toVO() {
        ProjectVO vo = new ProjectVO();
        vo.setId(this.id);
        vo.setName(this.name);
        vo.setDescription(this.description);
        return vo;
    }
    
    public ProjectPO toPO() {
        ProjectPO po = new ProjectPO();
        po.setId(this.id);
        po.setName(this.name);
        po.setDescription(this.description);
        return po;
    }
}
```

### 6.6 单元测试示例

```java
@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;
    
    @MockBean
    private ProjectManager projectManager;
    
    @Test
    public void testGetProject_Success() {
        // Given
        Long projectId = 1L;
        ProjectBO expectedProject = new ProjectBO();
        when(projectManager.getProjectById(projectId)).thenReturn(expectedProject);
        
        // When
        ProjectBO actualProject = projectService.getProject(projectId);
        
        // Then
        assertNotNull(actualProject);
        assertEquals(expectedProject, actualProject);
        verify(projectManager).getProjectById(projectId);
    }
    
    @Test
    public void testGetProject_NotFound() {
        // Given
        Long projectId = 1L;
        when(projectManager.getProjectById(projectId)).thenReturn(null);
        
        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getProject(projectId);
        });
    }
}
