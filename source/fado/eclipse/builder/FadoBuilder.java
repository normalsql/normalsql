package fado.eclipse.builder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import joptsimple.OptionParser;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import fado.Fado;
import fado.FadoOptions;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaProject;

public class 
	FadoBuilder 
extends 
	IncrementalProjectBuilder
{
	Fado _fado = null;
	
	public FadoBuilder()
	{
		FadoOptions options = new FadoOptions();
		String[] args = new String[]{};
		options.parse( args );
		System.out.println( options );
		_fado = new Fado();
		try
		{
			_fado.init( options );
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class 
		FadoDeltaVisitor 
	implements 
		IResourceDeltaVisitor
	{
		/*
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit( IResourceDelta delta ) 
			throws CoreException
		{
			IResource resource = delta.getResource();
			switch( delta.getKind() )
			{
				case IResourceDelta.ADDED:
					// handle added resource
					checkSQL( resource );
					break;
				case IResourceDelta.REMOVED:
					// handle removed resource
					break;
				case IResourceDelta.CHANGED:
					// handle changed resource
					checkSQL( resource );
					break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	class 
		FadoResourceVisitor 
	implements 
		IResourceVisitor
	{
		public boolean visit( IResource resource )
		{
			checkSQL( resource );
			// return true to continue visiting children.
			return true;
		}
	}

	public static final String BUILDER_ID = "fado.eclipse.fadoBuilder";

	private static final String MARKER_TYPE = "fado.eclipse.fadoProblem";


	private void addMarker( IFile file, String message, int lineNumber, int severity )
	{
		try
		{
			IMarker marker = file.createMarker( MARKER_TYPE );
			marker.setAttribute( IMarker.MESSAGE, message );
			marker.setAttribute( IMarker.SEVERITY, severity );
			if( lineNumber == -1 )
			{
				lineNumber = 1;
			}
			marker.setAttribute( IMarker.LINE_NUMBER, lineNumber );
		}
		catch( CoreException e )
		{
		}
	}

	/*
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int, java.util.Map,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build( int kind, Map args, IProgressMonitor monitor ) 
		throws CoreException
	{
		IProject project = getProject();
		if( project.isOpen() && JavaProject.hasJavaNature( project )) 
		{
			IJavaProject javaProject = JavaCore.create( project );
		}
		
		if( kind == FULL_BUILD )
		{
			fullBuild( monitor );
		}
		else
		{
			IResourceDelta delta = getDelta( getProject() );
			if( delta == null )
			{
				fullBuild( monitor );
			}
			else
			{
				incrementalBuild( delta, monitor );
			}
		}
		return null;
	}

	void checkSQL( IResource resource )
	{
		if( resource instanceof IFile && resource.getName().endsWith( ".sql" ))
		{
			IFile file = (IFile) resource;
			deleteMarkers( file );
			try
			{
				System.out.println( resource.getName() );
				IContainer parent = file.getParent();
				if( parent instanceof IFolder )
				{
					IFolder parentFolder = (IFolder) parent;
					
					IPath ipath = parentFolder.getFullPath();
					String[] path = ipath.segments();
					String pkg = _fado.join( path, "." );
					
					String name = resource.getName();
					
					IPath location = parentFolder.getLocation();
					File targetRoot = location.toFile();
					
					File sourceFile = file.getLocation().toFile();
					
					_fado.process( pkg, name, sourceFile, targetRoot );
//					byte[] bytes = writer.toString().getBytes();
//					
//					InputStream source = new ByteArrayInputStream( bytes );
//					target.create(source, IResource.NONE, null );
//					target.setDerived( true, null );
				}
			}
			catch( Exception e1 )
			{
				e1.printStackTrace();
			}
		}
	}

	private void deleteMarkers( IFile file )
	{
		try
		{
			file.deleteMarkers( MARKER_TYPE, false, IResource.DEPTH_ZERO );
		}
		catch( CoreException ce )
		{
		}
	}

	protected void fullBuild( final IProgressMonitor monitor ) 
		throws CoreException
	{
		try
		{
			IProject project = getProject();
			project.accept( new FadoResourceVisitor() );
		}
		catch( CoreException e )
		{
		}
	}

	protected void incrementalBuild( IResourceDelta delta, IProgressMonitor monitor ) 
		throws CoreException
	{
		// the visitor does the work.
		delta.accept( new FadoDeltaVisitor() );
	}
}
