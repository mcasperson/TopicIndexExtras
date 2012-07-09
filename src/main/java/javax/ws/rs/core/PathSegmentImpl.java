package javax.ws.rs.core;

import javax.ws.rs.core.MultivaluedMapImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * 
 *         Copied here to provide an implementation that can be used with GWT. Removed any references to org.jboss.resteasy.util.Encode.
 */
public class PathSegmentImpl
{
	private String path;
	private String original;
	private MultivaluedMapImpl matrixParameters = new MultivaluedMapImpl();

	public PathSegmentImpl()
	{

	}

	/**
	 * @param segment
	 *            encoded path segment
	 */
	public PathSegmentImpl(final String segment)
	{
		construct(segment);
	}

	public void construct(final String segment)
	{
		this.original = segment;
		this.path = segment;
		int semicolon = segment.indexOf(';');
		if (semicolon >= 0)
		{
			if (semicolon > 0)
				this.path = segment.substring(0, semicolon);
			else
				this.path = "";
			String matrixParams = segment.substring(semicolon + 1);
			String[] params = matrixParams.split(";");
			for (String param : params)
			{
				String[] namevalue = param.split("=");
				if (namevalue != null && namevalue.length > 0)
				{
					String name = namevalue[0];
					String value = "";
					if (namevalue.length > 1)
					{
						value = namevalue[1];
					}
					matrixParameters.add(name, value);
				}
			}
		}
	}

	public String getOriginal()
	{
		return original;
	}

	public String getPath()
	{
		return path;
	}

	public MultivaluedMapImpl getMatrixParameters()
	{
		return matrixParameters;
	}

	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		if (path != null)
			buf.append(path);
		if (matrixParameters != null)
		{
			for (String name : matrixParameters.keySet())
			{
				for (String value : matrixParameters.get(name))
				{
					buf.append(";").append(name).append("=").append(value);
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param path
	 *            encoded full path
	 * @param decode
	 *            whether or not to decode each segment
	 * @return
	 */
	public static List<PathSegmentImpl> parseSegments(String path)
	{
		List<PathSegmentImpl> pathSegments = new ArrayList<PathSegmentImpl>();

		if (path.startsWith("/"))
			path = path.substring(1);
		String[] paths = path.split("/");
		for (String p : paths)
		{
			pathSegments.add(new PathSegmentImpl(p));
		}
		return pathSegments;
	}
}
