package pe.modelo.pojo;
// Generated 15-ago-2017 17:21:31 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Menu generated by hbm2java
 */
@Entity
@Table(name="menu"
    ,schema="administracion"
)
public class Menu  implements java.io.Serializable {


     private long id;
     private Menu menu;
     private Usuario usuarioByIdUsuarioModificacion;
     private Usuario usuarioByIdUsuarioCreacion;
     private String nombre;
     private String url;
     private boolean activo;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private Set<Operacion> operacions = new HashSet<Operacion>(0);
     private Set<RolMenu> rolMenus = new HashSet<RolMenu>(0);
     private Set<Menu> menus = new HashSet<Menu>(0);

    public Menu() {
    }

	
    public Menu(long id, Usuario usuarioByIdUsuarioCreacion, String nombre, boolean activo, Date fechaCreacion) {
        this.id = id;
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
        this.nombre = nombre;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }
    public Menu(long id, Menu menu, Usuario usuarioByIdUsuarioModificacion, Usuario usuarioByIdUsuarioCreacion, String nombre, String url, boolean activo, Date fechaCreacion, Date fechaModificacion, Set<Operacion> operacions, Set<RolMenu> rolMenus, Set<Menu> menus) {
       this.id = id;
       this.menu = menu;
       this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
       this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
       this.nombre = nombre;
       this.url = url;
       this.activo = activo;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
       this.operacions = operacions;
       this.rolMenus = rolMenus;
       this.menus = menus;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_menu_padre")
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_modificacion")
    public Usuario getUsuarioByIdUsuarioModificacion() {
        return this.usuarioByIdUsuarioModificacion;
    }
    
    public void setUsuarioByIdUsuarioModificacion(Usuario usuarioByIdUsuarioModificacion) {
        this.usuarioByIdUsuarioModificacion = usuarioByIdUsuarioModificacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_creacion", nullable=false)
    public Usuario getUsuarioByIdUsuarioCreacion() {
        return this.usuarioByIdUsuarioCreacion;
    }
    
    public void setUsuarioByIdUsuarioCreacion(Usuario usuarioByIdUsuarioCreacion) {
        this.usuarioByIdUsuarioCreacion = usuarioByIdUsuarioCreacion;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="url", length=100)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
    @Column(name="activo", nullable=false)
    public boolean isActivo() {
        return this.activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", nullable=false, length=29)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_modificacion", length=29)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="menu")
    public Set<Operacion> getOperacions() {
        return this.operacions;
    }
    
    public void setOperacions(Set<Operacion> operacions) {
        this.operacions = operacions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="menu")
    public Set<RolMenu> getRolMenus() {
        return this.rolMenus;
    }
    
    public void setRolMenus(Set<RolMenu> rolMenus) {
        this.rolMenus = rolMenus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="menu")
    public Set<Menu> getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }




}


